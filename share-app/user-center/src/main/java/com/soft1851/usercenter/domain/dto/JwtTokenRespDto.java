package com.soft1851.usercenter.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 * @version 1.0
 * @ClassName JwtTokenRespDto
 * @Description 返回结果中的Jwt数据对象
 * @date 2020-10-13 15:37
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtTokenRespDto {
    /**
     * token
     */
    private String token;
    /**
     * 过期时间
     */
    private Long expirationTime;
}
