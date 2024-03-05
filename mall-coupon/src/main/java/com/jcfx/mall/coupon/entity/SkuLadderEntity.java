package com.jcfx.mall.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ??Ʒ???ݼ۸
 *
 * @author Bob Shaw
 * @email hninee@163.com
 * @date 2022-12-21 09:39:40
 */
@Data
@TableName("sms_sku_ladder")
public class SkuLadderEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * spu_id
     */
    private Long skuId;
    /**
     * ?????
     */
    private Integer fullCount;
    /**
     * ?????
     */
    private BigDecimal discount;
    /**
     * ?ۺ
     */
    private BigDecimal price;
    /**
     * ?Ƿ??????????Ż?[0-???ɵ??ӣ?1-?ɵ???]
     */
    private Integer addOther;

}
