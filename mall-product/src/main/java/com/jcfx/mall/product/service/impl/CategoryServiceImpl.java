package com.jcfx.mall.product.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jcfx.common.utils.PageUtils;
import com.jcfx.common.utils.Query;
import com.jcfx.mall.product.dao.CategoryDao;
import com.jcfx.mall.product.entity.CategoryEntity;
import com.jcfx.mall.product.service.CategoryBrandRelationService;
import com.jcfx.mall.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Slf4j
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private RedissonClient redissonClient;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

    /**
     * @return list
     * @title: treeList
     * @description: <p>从缓存中获取树形列表</p>
     * @author: NFFive
     * @date: 2024/6/6 13:18
     */
    @Override
    public List<CategoryEntity> treeList() {
        /**
         * 1.空结果缓存，预防缓存穿透
         * 2.设置随机的过期时间，解决缓存雪崩问题
         * 3.加锁，解决缓存击穿问题
         */

        // 先从缓存中查
        String catalogString = redisTemplate.opsForValue().get("catalogTreeList");
        List<CategoryEntity> treeList;
        if (StrUtil.isNotBlank(catalogString)) {
            // 缓存命中
            log.info("缓存命中，直接返回");
            treeList = JSON.parseArray(catalogString, CategoryEntity.class);
        } else {
            // 缓存未命中
            log.info("缓存未命中");
            treeList = treeListFromDb();
            // 放缓存不能放在这，假设一个线程执行完上一行代码，还没来得及放入Redis，这时候另一个线程拿到锁，发现缓存中没有，还是会再查数据库
            //redisTemplate.opsForValue().set("catalogTreeList", JSON.toJSONString(treeList), 1, TimeUnit.DAYS);
        }

        return treeList;
    }

    /**
     * @return list 树形列表
     * @title: treeList
     * @description: <p>商品分类的树形列表,使用Redisson分布式锁 锁住</p>
     * @author: NFFive
     * @date: 2024/6/6 12:47
     */
    public List<CategoryEntity> treeListFromDb() {
        RLock lock = redissonClient.getLock("catalogTree-lock");
        lock.lock();

        List<CategoryEntity> result = new ArrayList<>();
        try {
            // 双检查机制
            String catalogString = redisTemplate.opsForValue().get("catalogTreeList");
            if (StrUtil.isBlank(catalogString)) {
                // 1 查出所有分类
                log.info("查询了数据库");
                List<CategoryEntity> categories = this.list();

                // 2 过滤出其中的一级分类，即parentCid=0的，再调递归方法找到子菜单，然后对一级分类进行排序
                result = categories.stream().filter(categoryEntity -> categoryEntity.getParentCid() == 0)
                        .peek(category -> category.setChildren(getChildren(category, categories)))
                        .sorted(Comparator.comparingInt(cat -> (cat.getSort() == null ? 0 : cat.getSort())))
                        .collect(Collectors.toList());

                // 放入缓存（要保证只有一个线程能放，所以也是放在同步代码块中执行）
                redisTemplate.opsForValue().set("catalogTreeList", JSON.toJSONString(result), 1, TimeUnit.DAYS);

            } else {
                result = JSON.parseArray(catalogString, CategoryEntity.class);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            lock.unlock();
        }
        return result;
    }

    @Override
    public void removeCatByIds(List<Long> asList) {
        baseMapper.deleteBatchIds(asList);
    }

    // 目标格式[2,25,225]
    @Override
    public Long[] getCatlogPath(Long catelogId) {
        List<Long> paths = new ArrayList<>();
        List<Long> parent = getParent(catelogId, paths);
        Collections.reverse(parent);
        return parent.toArray(new Long[parent.size()]);
    }

    @Transactional
    @Override
    public void updateCascade(CategoryEntity category) {
        this.updateById(category);
        // 级联更新，分类-品牌关联表中存储了分类名
        if (!StringUtils.isEmpty(category.getName())) {
            categoryBrandRelationService.updateCategoryName(category.getCatId(), category.getName());
        }
    }

    /**
     * @return list
     * @title: levelOneList
     * @description: <p>返回顶级分类</p>
     * @author: NFFive
     * @date: 2024/6/6 20:40
     */
    @Cacheable(value = {"category"}, key = "#root.method.name")
    @Override
    public List<CategoryEntity> levelOneList() {
        return this.list(new QueryWrapper<CategoryEntity>().lambda().eq(CategoryEntity::getParentCid, 0));
    }

    /**
     * @param current 当前分类
     * @param all     所有分类
     * @return 递归查找当前分类的子分类
     */
    private List<CategoryEntity> getChildren(CategoryEntity current, List<CategoryEntity> all) {
        List<CategoryEntity> children = all.stream().filter(category -> category.getParentCid() == current.getCatId())
                .map(category -> {
                    category.setChildren(getChildren(category, all));
                    return category;
                }).sorted(Comparator.comparingInt(cat -> (cat.getSort() == null ? 0 : cat.getSort())))
                .collect(Collectors.toList());

        return children;
    }

    /**
     * 递归查找分类树[225,25,2]
     *
     * @param current 当前分类ID
     * @param paths
     * @return
     */
    private List<Long> getParent(Long current, List<Long> paths) {
        paths.add(current);
        CategoryEntity byId = this.getById(current);
        if (byId.getParentCid() != 0) {//还有父分类
            getParent(byId.getParentCid(), paths);
        }
        return paths;
    }

}