package com.soft1851.contentcenter.feignclient;

import com.soft1851.contentcenter.configuration.UserCenterFeignConfiguration;
import com.soft1851.contentcenter.domain.dto.UserAddBonusMsgDto;
import com.soft1851.contentcenter.domain.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Tao
 * @version 1.0
 * @ClassName UserCenterFeignClient
 * @Description TODO
 * @date 2020-09-29 19:07
 **/
@FeignClient(name = "user-center",configuration = UserCenterFeignConfiguration.class)
//@FeignClient(name = "user-center")
public interface UserCenterFeignClient {

    /**
     * http://user-center/user/{id}
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    UserDto findUserById(@PathVariable Integer id);


    /**
     * 给用户加积分（同步）
     * http://user-center/user/add/bonus
     * @param userAddBonusMsgDto
     * @return
     */
    @PutMapping(value = "/user/add/bonus")
    Integer addBonusToUser(@RequestBody UserAddBonusMsgDto userAddBonusMsgDto);


}
