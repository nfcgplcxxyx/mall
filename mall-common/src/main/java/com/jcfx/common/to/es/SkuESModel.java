package com.jcfx.common.to.es;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SkuESModel {
    private Long skuId;

    private String skuTitle;

    private BigDecimal skuPrice;

    private String skuImg;

    private Long spuId;

    private Long saleCount;

    private Boolean hasStock;

    private Long hotScore;

    private Long brandId;

    private Long catalogId;

    private String catalogName;

    private String brandName;

    private String brandImg;

    private List<Attrs> attrs;

    /**
     * @className: Attrs
     * @description: <p>属性</p>
     * @author：NFFive
     * @date: 2024/6/5 22:45
     * ${tags}$
     */
    @Data
    public static class Attrs {
        private Long attrId;
        private String attrName;
        private String attrValue;
    }
}
