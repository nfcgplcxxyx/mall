package com.jcfx.mall.search.service;

import com.jcfx.common.to.es.SkuESModel;

import java.io.IOException;
import java.util.List;

public interface ESSaveService {

    void up(List<SkuESModel> list) throws IOException;
}
