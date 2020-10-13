package com.soft1851.usercenter.domain.dto;

import com.soft1851.usercenter.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 * @version 1.0
 * @ClassName LoginResDto
 * @Description 登录返回结果
 * @date 2020-10-12 13:14
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResDto {
    private User user;
    private String token;
}
