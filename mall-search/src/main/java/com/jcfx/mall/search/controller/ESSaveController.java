package com.jcfx.mall.search.controller;

import com.jcfx.common.exception.BizCodeEnum;
import com.jcfx.common.to.es.SkuESModel;
import com.jcfx.common.utils.R;
import com.jcfx.mall.search.service.ESSaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("/search")
@RestController
public class ESSaveController {

    @Autowired
    private ESSaveService saveService;

    /**
     * @param list 商品列表
     * @return R
     * @title: up
     * @description: <p>上架商品，存入ElasticSearch中</p>
     * @author: NFFive
     * @date: 2024/6/5 22:27
     */
    @PostMapping("/product")
    public R up(@RequestBody List<SkuESModel> list) {
        try {
            saveService.up(list);
            return R.ok();
        } catch (Exception e) {
            log.error("商品上架错误：{}", e.getMessage());
            return R.error(BizCodeEnum.PRODUCT_UP_EXCEPTION.getCode(), BizCodeEnum.PRODUCT_UP_EXCEPTION.getMsg());
        }

    }
}
