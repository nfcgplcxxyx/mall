package com.jcfx.mall.product.service.impl;

import com.jcfx.mall.product.service.CategoryBrandRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jcfx.common.utils.PageUtils;
import com.jcfx.common.utils.Query;

import com.jcfx.mall.product.dao.BrandDao;
import com.jcfx.mall.product.entity.BrandEntity;
import com.jcfx.mall.product.service.BrandService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        QueryWrapper<BrandEntity> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(key)) { //说明需要模糊检索
            queryWrapper.eq("brand_id", key).or().like("name", key);
        }
        IPage<BrandEntity> page = this.page(new Query<BrandEntity>().getPage(params), queryWrapper);

        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void updateDetail(BrandEntity brand) {
        // 为了效率，很多表都存了品牌名，要保证一更新大家一起更新
        // 先更品牌表自己
        this.updateById(brand);
        if (!StringUtils.isEmpty(brand.getName())) {
            categoryBrandRelationService.updateBrandName(brand.getBrandId(), brand.getName());
        }
    }

}