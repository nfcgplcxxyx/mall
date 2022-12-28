package com.jcfx.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jcfx.common.utils.PageUtils;
import com.jcfx.mall.coupon.entity.CouponEntity;

import java.util.Map;

/**
 * ?Ż?ȯ??Ϣ
 *
 * @author Bob Shaw
 * @email hninee@163.com
 * @date 2022-12-21 09:39:39
 */
public interface CouponService extends IService<CouponEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

