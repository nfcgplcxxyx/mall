package com.jcfx.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jcfx.common.to.SkuReductionTo;
import com.jcfx.common.utils.PageUtils;
import com.jcfx.mall.coupon.entity.SkuFullReductionEntity;

import java.util.Map;

/**
 * ??Ʒ??????Ϣ
 *
 * @author Bob Shaw
 * @email hninee@163.com
 * @date 2022-12-21 09:39:40
 */
public interface SkuFullReductionService extends IService<SkuFullReductionEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSkuReduction(SkuReductionTo reductionTo);
}

