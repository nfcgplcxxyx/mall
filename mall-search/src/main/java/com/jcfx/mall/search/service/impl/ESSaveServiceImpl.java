package com.jcfx.mall.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.jcfx.common.to.es.SkuESModel;
import com.jcfx.mall.search.config.ESConfig;
import com.jcfx.mall.search.constant.ESConstant;
import com.jcfx.mall.search.service.ESSaveService;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ESSaveServiceImpl implements ESSaveService {

    @Autowired
    private RestHighLevelClient client;

    /**
     * @param list 商品列表
     * @throws IOException io
     * @title: up
     * @description: <p>上架商品，存入ElasticSearch中</p>
     * @author: NFFive
     * @date: 2024/6/5 22:27
     */
    @Override
    public void up(List<SkuESModel> list) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();

        list.forEach(model -> {
            IndexRequest request = new IndexRequest(ESConstant.PRODUCT_INDEX);
            request.id(model.getSkuId().toString());
            request.source(JSON.toJSONString(model), XContentType.JSON);
            bulkRequest.add(request);
        });

        client.bulk(bulkRequest, ESConfig.COMMON_OPTIONS);
    }
}
