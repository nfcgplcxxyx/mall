package com.jcfx.mall.product.controller;

import com.jcfx.common.utils.PageUtils;
import com.jcfx.common.utils.R;
import com.jcfx.mall.product.entity.AttrEntity;
import com.jcfx.mall.product.entity.AttrGroupEntity;
import com.jcfx.mall.product.service.AttrAttrgroupRelationService;
import com.jcfx.mall.product.service.AttrGroupService;
import com.jcfx.mall.product.service.AttrService;
import com.jcfx.mall.product.service.CategoryService;
import com.jcfx.mall.product.vo.AttrGroupRelationVo;
import com.jcfx.mall.product.vo.AttrGroupWithAttrsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * ???Է??
 *
 * @author Bob Shaw
 * @email hninee@163.com
 * @date 2022-12-20 22:35:23
 */
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AttrService attrService;
    @Autowired
    private AttrAttrgroupRelationService relationService;

    /**
     * 列表
     */
    @RequestMapping("/list/{catlogId}")
    public R list(@RequestParam Map<String, Object> params, @PathVariable Long catlogId) {
        PageUtils page = attrGroupService.queryPage(params, catlogId);

        return R.ok().put("page", page);
    }

    /**
     * 获取某一分类下的属性分组和每组拥有的具体属性
     *
     * @param catelogId
     * @return
     */
    @GetMapping("/{catelogId}/withattr")
    public R getByCatelogId(@PathVariable("catelogId") Long catelogId) {
        List<AttrGroupWithAttrsVo> vos = attrGroupService.getAttrGroupWithAttrsByCatlogId(catelogId);

        return R.ok().put("data", vos);
    }

    /**
     * 查询与当前分组相关联的属性
     *
     * @param attrgroupId
     * @return
     */
    @GetMapping("/{attrgroupId}/attr/relation")
    public R attrRelation(@PathVariable("attrgroupId") Long attrgroupId) {
        List<AttrEntity> entities = attrService.getRelationAttr(attrgroupId);

        return R.ok().put("data", entities);
    }

    /**
     * 查询分组没有关联的属性
     *
     * @param attrgroupId
     * @param params
     * @return
     */
    @GetMapping("/{attrgroupId}/noattr/relation")
    public R attrNoRelation(@PathVariable("attrgroupId") Long attrgroupId,
                            @RequestParam Map<String, Object> params) {
        PageUtils page = attrService.getNoRelationAttr(params, attrgroupId);

        return R.ok().put("page", page);
    }

    /**
     * 新增关联关系
     *
     * @param vos
     * @return
     */
    @PostMapping("/attr/relation")
    public R newRelation(@RequestBody List<AttrGroupRelationVo> vos) {
        relationService.saveBatch(vos);
        return R.ok();
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrGroupId}")
    public R info(@PathVariable("attrGroupId") Long attrGroupId) {
        AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);
        Long catelogId = attrGroup.getCatelogId();
        Long[] path = categoryService.getCatlogPath(catelogId);
        attrGroup.setCatelogPath(path);

        return R.ok().put("attrGroup", attrGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AttrGroupEntity attrGroup) {
        attrGroupService.save(attrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AttrGroupEntity attrGroup) {
        attrGroupService.updateById(attrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] attrGroupIds) {
        attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return R.ok();
    }

    /**
     * 删除当前组中已关联的属性
     *
     * @param vos
     * @return
     */
    @RequestMapping("/attr/relation/delete")
    public R deleteRelation(@RequestBody AttrGroupRelationVo[] vos) {
        attrService.deleteRelation(vos);
        return R.ok();
    }

}
