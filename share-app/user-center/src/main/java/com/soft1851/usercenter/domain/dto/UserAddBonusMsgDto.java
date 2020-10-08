package com.soft1851.usercenter.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 * @version 1.0
 * @ClassName UserAddBonusMsgDto
 * @Description TODO
 * @date 2020-10-08 11:11
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAddBonusMsgDto {
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 增加积分
     */
    private Integer bonus;
}
