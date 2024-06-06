package com.jcfx.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jcfx.common.utils.PageUtils;
import com.jcfx.mall.product.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author Bob Shaw
 * @email hninee@163.com
 * @date 2022-12-20 22:35:23
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * @return list 树形列表
     * @title: treeList
     * @description: <p>商品分类的树形列表</p>
     * @author: NFFive
     * @date: 2024/6/6 12:47
     */
    List<CategoryEntity> treeList();

    void removeCatByIds(List<Long> asList);

    Long[] getCatlogPath(Long catelogId);

    void updateCascade(CategoryEntity category);

    List<CategoryEntity> levelOneList();

}

