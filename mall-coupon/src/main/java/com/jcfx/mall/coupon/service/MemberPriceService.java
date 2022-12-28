package com.jcfx.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jcfx.common.utils.PageUtils;
import com.jcfx.mall.coupon.entity.MemberPriceEntity;

import java.util.Map;

/**
 * ??Ʒ??Ա?۸
 *
 * @author Bob Shaw
 * @email hninee@163.com
 * @date 2022-12-21 09:39:40
 */
public interface MemberPriceService extends IService<MemberPriceEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

