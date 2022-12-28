package com.jcfx.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jcfx.common.utils.PageUtils;
import com.jcfx.mall.member.entity.MemberCollectSubjectEntity;

import java.util.Map;

/**
 * ??Ա?ղص?ר???
 *
 * @author Bob Shaw
 * @email hninee@163.com
 * @date 2022-12-21 09:58:04
 */
public interface MemberCollectSubjectService extends IService<MemberCollectSubjectEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

