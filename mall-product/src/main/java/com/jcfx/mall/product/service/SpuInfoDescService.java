package com.jcfx.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jcfx.common.utils.PageUtils;
import com.jcfx.mall.product.entity.SpuInfoDescEntity;

import java.util.Map;

/**
 * spu??Ϣ???
 *
 * @author Bob Shaw
 * @email hninee@163.com
 * @date 2022-12-20 22:35:23
 */
public interface SpuInfoDescService extends IService<SpuInfoDescEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSpuInfoDesc(SpuInfoDescEntity descEntity);
}

