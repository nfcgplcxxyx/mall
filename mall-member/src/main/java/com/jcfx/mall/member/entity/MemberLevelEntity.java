package com.jcfx.mall.member.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ??Ա?ȼ?
 *
 * @author Bob Shaw
 * @email hninee@163.com
 * @date 2022-12-21 09:58:04
 */
@Data
@TableName("ums_member_level")
public class MemberLevelEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * ?ȼ??
     */
    private String name;
    /**
     * ?ȼ???Ҫ?ĳɳ?ֵ
     */
    private Integer growthPoint;
    /**
     * ?Ƿ?ΪĬ?ϵȼ?[0->???ǣ?1->??]
     */
    private Integer defaultStatus;
    /**
     * ???˷ѱ?׼
     */
    private BigDecimal freeFreightPoint;
    /**
     * ÿ?????ۻ?ȡ?ĳɳ?ֵ
     */
    private Integer commentGrowthPoint;
    /**
     * ?Ƿ?????????Ȩ
     */
    private Integer priviledgeFreeFreight;
    /**
     * ?Ƿ??л?Ա?۸???Ȩ
     */
    private Integer priviledgeMemberPrice;
    /**
     * ?Ƿ?????????Ȩ
     */
    private Integer priviledgeBirthday;
    /**
     * ??ע
     */
    private String note;

}
