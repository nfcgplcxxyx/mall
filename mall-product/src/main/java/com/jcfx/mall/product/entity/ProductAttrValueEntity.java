package com.jcfx.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * spu????ֵ
 *
 * @author Bob Shaw
 * @email hninee@163.com
 * @date 2022-12-20 22:35:23
 */
@Data
@TableName("pms_product_attr_value")
public class ProductAttrValueEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * ??Ʒid
     */
    private Long spuId;
    /**
     * ????id
     */
    private Long attrId;
    /**
     * ?????
     */
    private String attrName;
    /**
     * ????ֵ
     */
    private String attrValue;
    /**
     * ˳?
     */
    private Integer attrSort;
    /**
     * ????չʾ???Ƿ?չʾ?ڽ????ϣ?0-?? 1-?ǡ?
     */
    private Integer quickShow;

}
