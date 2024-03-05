package com.jcfx.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jcfx.common.utils.PageUtils;
import com.jcfx.mall.product.entity.AttrEntity;
import com.jcfx.mall.product.vo.AttrGroupRelationVo;
import com.jcfx.mall.product.vo.AttrRespVo;
import com.jcfx.mall.product.vo.AttrVo;

import java.util.List;
import java.util.Map;

/**
 * ??Ʒ?
 *
 * @author Bob Shaw
 * @email hninee@163.com
 * @date 2022-12-20 22:35:23
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveAttr(AttrVo attr);

    PageUtils queryAttrPage(Map<String, Object> params, Long catlogId, String attrType);

    AttrRespVo getAttrInfo(Long attrId);

    void updateAttr(AttrVo attr);

    List<AttrEntity> getRelationAttr(Long attrgroupId);

    void deleteRelation(AttrGroupRelationVo[] vos);

    PageUtils getNoRelationAttr(Map<String, Object> params, Long attrgroupId);

    List<AttrEntity> getSearcheableAttrs(List<Long> attrIds);
}

