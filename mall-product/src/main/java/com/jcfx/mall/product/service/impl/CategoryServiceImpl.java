package com.jcfx.mall.product.service.impl;

import com.jcfx.mall.product.service.CategoryBrandRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jcfx.common.utils.PageUtils;
import com.jcfx.common.utils.Query;

import com.jcfx.mall.product.dao.CategoryDao;
import com.jcfx.mall.product.entity.CategoryEntity;
import com.jcfx.mall.product.service.CategoryService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> treeList() {
        // 1.查出所有分类(baseMapper就是categoryDao)
        List<CategoryEntity> categories = baseMapper.selectList(null);

        // 2.过滤出其中的一级分类，即parentCid=0的，再调递归方法找到子菜单，然后对一级分类进行排序
        List<CategoryEntity> firstClass = categories.stream().filter(categoryEntity -> categoryEntity.getParentCid() == 0)
                .map(category -> {
                    category.setChildren(getChildren(category, categories));
                    return category;
                })
                .sorted(Comparator.comparingInt(cat -> (cat.getSort() == null ? 0 : cat.getSort())))
                .collect(Collectors.toList());

        return firstClass;
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
        if (!StringUtils.isEmpty(category.getName())) {
            categoryBrandRelationService.updateCategoryName(category.getCatId(), category.getName());
        }
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