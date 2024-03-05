package com.jcfx.mall.member.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ??Աͳ????Ϣ
 *
 * @author Bob Shaw
 * @email hninee@163.com
 * @date 2022-12-21 09:58:04
 */
@Data
@TableName("ums_member_statistics_info")
public class MemberStatisticsInfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * ??Աid
     */
    private Long memberId;
    /**
     * ?ۼ????ѽ
     */
    private BigDecimal consumeAmount;
    /**
     * ?ۼ??Żݽ
     */
    private BigDecimal couponAmount;
    /**
     * ?????
     */
    private Integer orderCount;
    /**
     * ?Ż?ȯ?
     */
    private Integer couponCount;
    /**
     * ?????
     */
    private Integer commentCount;
    /**
     * ?˻??
     */
    private Integer returnOrderCount;
    /**
     * ??¼???
     */
    private Integer loginCount;
    /**
     * ??ע?
     */
    private Integer attendCount;
    /**
     * ??˿?
     */
    private Integer fansCount;
    /**
     * ?ղص???Ʒ?
     */
    private Integer collectProductCount;
    /**
     * ?ղص?ר????
     */
    private Integer collectSubjectCount;
    /**
     * ?ղص??????
     */
    private Integer collectCommentCount;
    /**
     * ???????????
     */
    private Integer inviteFriendCount;

}
