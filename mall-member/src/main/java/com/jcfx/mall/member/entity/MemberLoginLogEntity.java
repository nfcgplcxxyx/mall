package com.jcfx.mall.member.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * ??Ա??¼??¼
 *
 * @author Bob Shaw
 * @email hninee@163.com
 * @date 2022-12-21 09:58:04
 */
@Data
@TableName("ums_member_login_log")
public class MemberLoginLogEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * member_id
     */
    private Long memberId;
    /**
     * ????ʱ?
     */
    private Date createTime;
    /**
     * ip
     */
    private String ip;
    /**
     * city
     */
    private String city;
    /**
     * ??¼????[1-web??2-app]
     */
    private Integer loginType;

}
