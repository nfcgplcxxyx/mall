package com.jcfx.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jcfx.common.utils.PageUtils;
import com.jcfx.mall.coupon.entity.HomeSubjectEntity;

import java.util.Map;

/**
 * ??ҳר??????jd??ҳ?????ܶ?ר?⣬ÿ??ר???????µ?ҳ?棬չʾר????Ʒ??Ϣ??
 *
 * @author Bob Shaw
 * @email hninee@163.com
 * @date 2022-12-21 09:39:40
 */
public interface HomeSubjectService extends IService<HomeSubjectEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

