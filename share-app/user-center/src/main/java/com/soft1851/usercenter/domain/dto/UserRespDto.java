package com.soft1851.usercenter.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 * @version 1.0
 * @ClassName UserRespDto
 * @Description 返回结果中的用户数据对象
 * @date 2020-10-13 15:35
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRespDto {
    /**
     * id
     */
    private Integer id;
    /**
     * 头像地址
     */
    private String avatarUrl;
    /**
     * 积分
     */
    private Integer bonus;
    /**
     * 微信昵称
     */
    private String wxNickname;
}
