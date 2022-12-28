package com.jcfx.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jcfx.common.utils.PageUtils;
import com.jcfx.mall.order.entity.OrderEntity;

import java.util.Map;

/**
 * ????
 *
 * @author Bob Shaw
 * @email hninee@163.com
 * @date 2022-12-21 10:06:19
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

