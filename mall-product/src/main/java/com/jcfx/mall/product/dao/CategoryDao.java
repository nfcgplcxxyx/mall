package com.jcfx.mall.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jcfx.mall.product.entity.CategoryEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 *
 * @author Bob Shaw
 * @email hninee@163.com
 * @date 2022-12-20 22:35:23
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {

}
