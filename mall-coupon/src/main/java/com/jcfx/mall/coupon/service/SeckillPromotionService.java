package com.jcfx.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jcfx.common.utils.PageUtils;
import com.jcfx.mall.coupon.entity.SeckillPromotionEntity;

import java.util.Map;

/**
 * ??ɱ?
 *
 * @author Bob Shaw
 * @email hninee@163.com
 * @date 2022-12-21 09:39:40
 */
public interface SeckillPromotionService extends IService<SeckillPromotionEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

