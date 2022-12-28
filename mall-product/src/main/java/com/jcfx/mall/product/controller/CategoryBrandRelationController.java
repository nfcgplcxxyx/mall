package com.jcfx.mall.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jcfx.mall.product.entity.BrandEntity;
import com.jcfx.mall.product.vo.BrandVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jcfx.mall.product.entity.CategoryBrandRelationEntity;
import com.jcfx.mall.product.service.CategoryBrandRelationService;
import com.jcfx.common.utils.PageUtils;
import com.jcfx.common.utils.R;


/**
 * Ʒ?Ʒ???????
 *
 * @author Bob Shaw
 * @email hninee@163.com
 * @date 2022-12-20 22:35:23
 */
@RestController
@RequestMapping("product/categorybrandrelation")
public class CategoryBrandRelationController {
    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = categoryBrandRelationService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 当前品牌关联的所有分类列表
     *
     * @param brandId
     * @return
     */
    @GetMapping("/catelog/list")
    public R catelogList(@RequestParam("brandId") Long brandId) {
        QueryWrapper<CategoryBrandRelationEntity> queryWrapper = new QueryWrapper<CategoryBrandRelationEntity>().eq("brand_id", brandId);
        List<CategoryBrandRelationEntity> list = categoryBrandRelationService.list(queryWrapper);
        return R.ok().put("data", list);
    }

    /**
     * 查询某分类下的品牌有哪些
     *
     * @param catId
     * @return
     */
    @GetMapping("/brands/list")
    public R relationBrandsList(@RequestParam(value = "catId", required = true, defaultValue = "0") Long catId) {
        List<BrandEntity> entities = categoryBrandRelationService.getBrandsByCatId(catId);
        List<BrandVo> vos = entities.stream().map(brand -> {
            BrandVo brandVo = new BrandVo();
            brandVo.setBrandId(brand.getBrandId());
            brandVo.setBrandName(brand.getName());
            return brandVo;
        }).collect(Collectors.toList());
        return R.ok().put("data", vos);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        CategoryBrandRelationEntity categoryBrandRelation = categoryBrandRelationService.getById(id);

        return R.ok().put("categoryBrandRelation", categoryBrandRelation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CategoryBrandRelationEntity categoryBrandRelation) {
        categoryBrandRelationService.saveDetail(categoryBrandRelation);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CategoryBrandRelationEntity categoryBrandRelation) {
        categoryBrandRelationService.updateById(categoryBrandRelation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        categoryBrandRelationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
