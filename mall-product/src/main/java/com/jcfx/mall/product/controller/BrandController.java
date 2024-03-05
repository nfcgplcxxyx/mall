package com.jcfx.mall.product.controller;

import com.jcfx.common.utils.PageUtils;
import com.jcfx.common.utils.R;
import com.jcfx.mall.product.entity.BrandEntity;
import com.jcfx.mall.product.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Map;


/**
 * @author Bob Shaw
 * @email hninee@163.com
 * @date 2022-12-20 22:35:23
 */
@RestController
@RequestMapping("product/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = brandService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{brandId}")
    public R info(@PathVariable("brandId") Long brandId) {
        BrandEntity brand = brandService.getById(brandId);

        return R.ok().put("brand", brand);
    }

    /**
     * 保存,@Valid告诉了MVC要对body中的数据做校验
     */
    @RequestMapping("/save")
    //public R save(@Validated({AddGroup.class}) @RequestBody BrandEntity brand) {
//    public R save(@Valid @RequestBody BrandEntity brand, BindingResult result) {
    public R save(@Valid @RequestBody BrandEntity brand) {
//        if (result.hasErrors()) {
//            // 获取校验结果的详细信息
//            Map<String, String> map = new HashMap<>();
//            result.getFieldErrors().forEach(fieldError -> {
//                // 错误消息
//                String message = fieldError.getDefaultMessage();
//                // 错误的字段名
//                String fieldName = fieldError.getField();
//                map.put(fieldName, message);
//            });
//
//            return R.error("提交的数据不合法").put("data", map);
//        } else {
//            brandService.save(brand);
//            return R.ok();
//        }
        brandService.save(brand);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody BrandEntity brand) {
        brandService.updateDetail(brand);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] brandIds) {
        brandService.removeByIds(Arrays.asList(brandIds));

        return R.ok();
    }

}
