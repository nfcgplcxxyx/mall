package com.jcfx.mall.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ??Ʒ??Ա?۸
 *
 * @author Bob Shaw
 * @email hninee@163.com
 * @date 2022-12-21 09:39:40
 */
@Data
@TableName("sms_member_price")
public class MemberPriceEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * sku_id
     */
    private Long skuId;
    /**
     * ??Ա?ȼ?id
     */
    private Long memberLevelId;
    /**
     * ??Ա?ȼ??
     */
    private String memberLevelName;
    /**
     * ??Ա??Ӧ?۸
     */
    private BigDecimal memberPrice;
    /**
     * ?ɷ??????????Ż?[0-???ɵ????Żݣ?1-?ɵ???]
     */
    private Integer addOther;

}
