package com.soft1851.usercenter.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 * @version 1.0
 * @ClassName UserAddBonusDto
 * @Description TODO
 * @date 2020-10-14 23:33
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAddBonusDto {
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 积分
     */
    private Integer bonus;
}