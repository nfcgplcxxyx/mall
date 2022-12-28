package com.jcfx.mall.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.jcfx.mall.product.entity.ProductAttrValueEntity;
import com.jcfx.mall.product.service.ProductAttrValueService;
import com.jcfx.mall.product.vo.AttrRespVo;
import com.jcfx.mall.product.vo.AttrVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jcfx.mall.product.entity.AttrEntity;
import com.jcfx.mall.product.service.AttrService;
import com.jcfx.common.utils.PageUtils;
import com.jcfx.common.utils.R;


/**
 * ??Ʒ?
 *
 * @author Bob Shaw
 * @email hninee@163.com
 * @date 2022-12-20 22:35:23
 */
@RestController
@RequestMapping("product/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;
    @Autowired
    ProductAttrValueService productAttrValueService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = attrService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 获取商品规格属性
     *
     * @param spuId
     * @return
     */
    @GetMapping("/base/listforspu/{spuId}")
    public R baseAttrlistforspu(@PathVariable("spuId") Long spuId) {
        List<ProductAttrValueEntity> entities = productAttrValueService.baseAttrlistforspu(spuId);

        return R.ok().put("data", entities);
    }

    /**
     * @param params   分页参数
     * @param catlogId 三级分类ID
     * @param attrType 是基本属性还是销售属性
     * @return
     */
    @GetMapping("/{attrType}/list/{catlogId}")
    public R attrList(@RequestParam Map<String, Object> params,
                      @PathVariable("catlogId") Long catlogId,
                      @PathVariable("attrType") String attrType) {

        PageUtils page = attrService.queryAttrPage(params, catlogId, attrType);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrId}")
    public R info(@PathVariable("attrId") Long attrId) {
        // AttrEntity attr = attrService.getById(attrId);
        AttrRespVo attr = attrService.getAttrInfo(attrId);
        return R.ok().put("attr", attr);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AttrVo attr) {
        attrService.saveAttr(attr);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AttrVo attr) {
        attrService.updateAttr(attr);

        return R.ok();
    }

    /**
     * 修改
     *
     * @param spuId
     * @param entities
     * @return
     */
    @PostMapping("/update/{spuId}")
    public R updateSpuAttr(@PathVariable("spuId") Long spuId,
                           @RequestBody List<ProductAttrValueEntity> entities) {

        productAttrValueService.updateSpuAttr(spuId, entities);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] attrIds) {
        attrService.removeByIds(Arrays.asList(attrIds));

        return R.ok();
    }

}
