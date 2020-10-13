package com.soft1851.usercenter.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 * @version 1.0
 * @ClassName LoginDto
 * @Description 用户登录时的传输对象（客户端->后台）
 * @date 2020-10-12 13:04
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDto {
    /**
     * openId
     */
    private String openId;
    /**
     * loginCode
     */
    private String loginCode;
    /**
     * 微信昵称
     */
    private String wxNickname;
    /**
     * 头像地址
     */
    private String avatarUrl;
}
