package com.jcfx.mall.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * ??????????ʷ??¼
 *
 * @author Bob Shaw
 * @email hninee@163.com
 * @date 2022-12-21 10:06:19
 */
@Data
@TableName("oms_order_operate_history")
public class OrderOperateHistoryEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * ????id
     */
    private Long orderId;
    /**
     * ??????[?û???ϵͳ????̨????Ա]
     */
    private String operateMan;
    /**
     * ????ʱ?
     */
    private Date createTime;
    /**
     * ????״̬??0->?????1->????????2->?ѷ?????3->?????ɣ?4->?ѹرգ?5->??Ч??????
     */
    private Integer orderStatus;
    /**
     * ??ע
     */
    private String note;

}
