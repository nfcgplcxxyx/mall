package com.jcfx.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jcfx.common.utils.PageUtils;
import com.jcfx.mall.coupon.entity.SeckillSkuRelationEntity;

import java.util.Map;

/**
 * ??ɱ???Ʒ????
 *
 * @author Bob Shaw
 * @email hninee@163.com
 * @date 2022-12-21 09:39:40
 */
public interface SeckillSkuRelationService extends IService<SeckillSkuRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

