package com.soft1851.contentcenter.feignclient;

import com.soft1851.contentcenter.domain.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Tao
 * @version 1.0
 * @ClassName TestUserCenterFeignClient
 * @Description TODO
 * @date 2020-09-30 10:58
 **/
@FeignClient(name = "user-center")
public interface TestUserCenterFeignClient {

    /**
     * 多参数查询
     * @param userDto
     * @return
     */
    @GetMapping("/user/q")
    UserDto query(@SpringQueryMap UserDto userDto);
}
