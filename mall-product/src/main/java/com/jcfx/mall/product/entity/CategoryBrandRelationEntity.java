package com.jcfx.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * Ʒ?Ʒ???????
 *
 * @author Bob Shaw
 * @email hninee@163.com
 * @date 2022-12-20 22:35:23
 */
@Data
@TableName("pms_category_brand_relation")
public class CategoryBrandRelationEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Long id;
    /**
     * Ʒ??id
     */
    private Long brandId;
    /**
     * ????id
     */
    private Long catelogId;
    /**
     *
     */
    private String brandName;
    /**
     *
     */
    private String catelogName;

}
