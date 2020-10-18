package com.soft1851.usercenter.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 * @version 1.0
 * @ClassName UserSignInDTO
 * @Description TODO
 * @date 2020-10-16 8:22
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSignInDTO {
    private Integer userId;
}