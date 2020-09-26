package com.soft1851.usercenter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tao
 * @version 1.0
 * @ClassName UserHello
 * @Description TODO
 * @date 2020-09-23 9:53
 **/
@RestController
@RequestMapping(value = "/user")
@Slf4j
public class UserHello {
    @GetMapping(value = "/hello")
    public String getHello(){
        log.info("我被调用了");
        return "Hello,this message is from user-center!";
    }
}
