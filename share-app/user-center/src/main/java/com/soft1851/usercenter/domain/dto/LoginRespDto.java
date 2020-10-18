package com.soft1851.usercenter.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 * @version 1.0
 * @ClassName LoginRespDto
 * @Description 登录返回结果
 * @date 2020-10-13 15:40
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRespDto {
    /**
     * 用户信息
     */
    private UserRespDto user;

    /**
     * token数据
     */
    private JwtTokenRespDto token;
    /**
     * 用户当天是否签到
     */
    private Integer inUserSignin;
}
