package com.jcfx.mall.product.feign;

import com.jcfx.common.to.es.SkuESModel;
import com.jcfx.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("mall-search")
public interface ElasticFeignService {
    @PostMapping("/search/product")
    R up(@RequestBody List<SkuESModel> list);
}
