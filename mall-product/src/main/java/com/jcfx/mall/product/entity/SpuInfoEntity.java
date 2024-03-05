package com.jcfx.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * spu??Ϣ
 *
 * @author Bob Shaw
 * @email hninee@163.com
 * @date 2022-12-20 22:35:23
 */
@Data
@TableName("pms_spu_info")
public class SpuInfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ??Ʒid
     */
    @TableId
    private Long id;
    /**
     * ??Ʒ?
     */
    private String spuName;
    /**
     * ??Ʒ???
     */
    private String spuDescription;
    /**
     * ????????id
     */
    private Long catalogId;
    /**
     * Ʒ??id
     */
    private Long brandId;
    /**
     *
     */
    private BigDecimal weight;
    /**
     * ?ϼ?״̬[0 - ?¼ܣ?1 - ?ϼ?]
     */
    private Integer publishStatus;
    /**
     *
     */
    private Date createTime;
    /**
     *
     */
    private Date updateTime;

}
