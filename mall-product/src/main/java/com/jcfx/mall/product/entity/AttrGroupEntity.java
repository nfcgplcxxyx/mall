package com.jcfx.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * ???Է??
 *
 * @author Bob Shaw
 * @email hninee@163.com
 * @date 2022-12-20 22:35:23
 */
@Data
@TableName("pms_attr_group")
public class AttrGroupEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ????id
     */
    @TableId
    private Long attrGroupId;
    /**
     * ???
     */
    private String attrGroupName;
    /**
     * ???
     */
    private Integer sort;
    /**
     * ???
     */
    private String descript;
    /**
     * ??ͼ?
     */
    private String icon;
    /**
     * ????????id
     */
    private Long catelogId;

    @TableField(exist = false)
    private Long[] catelogPath;

}
