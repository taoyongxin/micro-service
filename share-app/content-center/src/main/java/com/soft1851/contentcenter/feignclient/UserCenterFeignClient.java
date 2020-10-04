package com.soft1851.contentcenter.feignclient;

import com.soft1851.contentcenter.configuration.UserCenterFeignConfiguration;
import com.soft1851.contentcenter.domain.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
}
