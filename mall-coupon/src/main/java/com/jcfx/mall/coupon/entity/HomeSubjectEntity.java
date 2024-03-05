package com.jcfx.mall.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * ??ҳר??????jd??ҳ?????ܶ?ר?⣬ÿ??ר???????µ?ҳ?棬չʾר????Ʒ??Ϣ??
 *
 * @author Bob Shaw
 * @email hninee@163.com
 * @date 2022-12-21 09:39:40
 */
@Data
@TableName("sms_home_subject")
public class HomeSubjectEntity implements Serializable {
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
     * ר?????
     */
    private String title;
    /**
     * ר?⸱???
     */
    private String subTitle;
    /**
     * ??ʾ״̬
     */
    private Integer status;
    /**
     * ???????
     */
    private String url;
    /**
     * ???
     */
    private Integer sort;
    /**
     * ר??ͼƬ??ַ
     */
    private String img;

}
