package com.jcfx.mall.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * ר????Ʒ
 *
 * @author Bob Shaw
 * @email hninee@163.com
 * @date 2022-12-21 09:39:40
 */
@Data
@TableName("sms_home_subject_spu")
public class HomeSubjectSpuEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * ר???
     */
    private String name;
    /**
     * ר??id
     */
    private Long subjectId;
    /**
     * spu_id
     */
    private Long spuId;
    /**
     * ???
     */
    private Integer sort;

}
