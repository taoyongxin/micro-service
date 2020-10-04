package com.soft1851.contentcenter.domain.dto;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Tao
 * @version 1.0
 * @ClassName UserDto
 * @Description TODO
 * @date 2020-09-24 20:50
 **/
@Data
public class UserDto {
    /**
     * Id
     */
    private Integer id;

    /**
     * 微信id
     */
    private String wxId;

    /**
     * 微信昵称
     */
    private String wxNickname;

    /**
     * 角色
     */
    private String roles;

    /**
     * 头像地址
     */
    private String avatarUrl;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 修改时间
     */
    private Timestamp updateTime;

    /**
     * 积分
     */
    private Integer bonus;
}
