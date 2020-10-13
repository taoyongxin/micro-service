package com.soft1851.usercenter.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 * @version 1.0
 * @ClassName WxLoginDto
 * @Description TODO
 * @date 2020-10-13 10:14
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WxLoginDto {
    private String code;
    private String wxNickname;
    private String avatarUrl;
}
