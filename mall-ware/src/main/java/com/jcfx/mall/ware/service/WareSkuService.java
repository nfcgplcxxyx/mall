package com.jcfx.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jcfx.common.to.SkuStockVO;
import com.jcfx.common.utils.PageUtils;
import com.jcfx.mall.ware.entity.WareSkuEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品库存
 *
 * @author Bob Shaw
 * @email hninee@163.com
 * @date 2022-12-21 10:14:58
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void addStock(Long skuId, Long wareId, Integer skuNum);

    List<SkuStockVO> hasStock(List<Long> skuIds);
}

