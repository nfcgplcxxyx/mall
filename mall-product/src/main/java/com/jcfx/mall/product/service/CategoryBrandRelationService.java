package com.jcfx.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jcfx.common.utils.PageUtils;
import com.jcfx.mall.product.entity.BrandEntity;
import com.jcfx.mall.product.entity.CategoryBrandRelationEntity;

import java.util.List;
import java.util.Map;

/**
 * Ʒ?Ʒ???????
 *
 * @author Bob Shaw
 * @email hninee@163.com
 * @date 2022-12-20 22:35:23
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveDetail(CategoryBrandRelationEntity categoryBrandRelation);

    void updateBrandName(Long brandId, String name);

    void updateCategoryName(Long catId, String name);

    List<BrandEntity> getBrandsByCatId(Long catId);
}

