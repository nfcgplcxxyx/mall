package com.jcfx.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jcfx.common.utils.PageUtils;
import com.jcfx.mall.order.entity.OrderOperateHistoryEntity;

import java.util.Map;

/**
 * ??????????ʷ??¼
 *
 * @author Bob Shaw
 * @email hninee@163.com
 * @date 2022-12-21 10:06:19
 */
public interface OrderOperateHistoryService extends IService<OrderOperateHistoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

