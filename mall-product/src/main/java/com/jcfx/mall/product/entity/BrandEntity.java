package com.jcfx.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;

/**
 * @author Bob Shaw
 * @email hninee@163.com
 * @date 2022-12-20 22:35:23
 */
@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Ʒ??id
     */
    @TableId
    private Long brandId;
    /**
     * 添加和修改的时候都要校验
     */
    //@NotBlank(message = "品牌名不能为空",groups = {AddGroup.class, UpdateGroup.class})
    @NotBlank(message = "品牌名不能为空")
    private String name;
    /**
     * Ʒ??logo??ַ
     */
    @NotEmpty
    @URL(message = "Logo应当是一个合法的URL地址")
    private String logo;
    /**
     * ???
     */
    private String descript;
    /**
     * ??ʾ״̬[0-????ʾ??1-??ʾ]
     */
    private Integer showStatus;
    /**
     * ????????ĸ
     */
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z]$", message = "必须是一个字母!")
    private String firstLetter;
    /**
     * ???
     */
    @NotNull
    @Min(value = 0, message = "不能为负")
    private Integer sort;

}
