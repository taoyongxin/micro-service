package com.soft1851.usercenter.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 * @version 1.0
 * @ClassName LoginDto
 * @Description TODO
 * @date 2020-10-12 13:04
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDto {
    private String wxId;
    private String wxNickname;
    private String avatarUrl;
}
