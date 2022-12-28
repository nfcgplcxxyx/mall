package com.jcfx.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jcfx.common.utils.PageUtils;
import com.jcfx.mall.member.entity.MemberLevelEntity;

import java.util.Map;

/**
 * ??Ա?ȼ?
 *
 * @author Bob Shaw
 * @email hninee@163.com
 * @date 2022-12-21 09:58:04
 */
public interface MemberLevelService extends IService<MemberLevelEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

