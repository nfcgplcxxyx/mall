package com.jcfx.mall.product.vo;

import lombok.Data;

@Data
public class AttrVo {

    private Long attrId;

    private String attrName;

    private Integer searchType;

    private String icon;

    private String valueSelect;

    private Integer attrType;

    private Long enable;

    private Long catelogId;

    private Integer showDesc;

    private Integer valueType;

    private Long attrGroupId;
}
