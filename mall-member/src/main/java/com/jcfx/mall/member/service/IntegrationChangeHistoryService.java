package com.jcfx.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jcfx.common.utils.PageUtils;
import com.jcfx.mall.member.entity.IntegrationChangeHistoryEntity;

import java.util.Map;

/**
 * ???ֱ仯??ʷ??¼
 *
 * @author Bob Shaw
 * @email hninee@163.com
 * @date 2022-12-21 09:58:04
 */
public interface IntegrationChangeHistoryService extends IService<IntegrationChangeHistoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

