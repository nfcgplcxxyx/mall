package com.jcfx.mall.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * ??ɱ?
 *
 * @author Bob Shaw
 * @email hninee@163.com
 * @date 2022-12-21 09:39:40
 */
@Data
@TableName("sms_seckill_promotion")
public class SeckillPromotionEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * ????
     */
    private String title;
    /**
     * ??ʼ???
     */
    private Date startTime;
    /**
     * ???????
     */
    private Date endTime;
    /**
     * ??????״̬
     */
    private Integer status;
    /**
     * ????ʱ?
     */
    private Date createTime;
    /**
     * ?????
     */
    private Long userId;

}
