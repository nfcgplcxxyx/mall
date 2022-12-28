package com.jcfx.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jcfx.common.utils.PageUtils;
import com.jcfx.mall.product.entity.SpuImagesEntity;

import java.util.List;
import java.util.Map;

/**
 * spuͼƬ
 *
 * @author Bob Shaw
 * @email hninee@163.com
 * @date 2022-12-20 22:35:23
 */
public interface SpuImagesService extends IService<SpuImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveImages(Long id, List<String> images);
}

