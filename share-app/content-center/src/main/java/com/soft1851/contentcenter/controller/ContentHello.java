package com.soft1851.contentcenter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tao
 * @version 1.0
 * @ClassName ContentHello
 * @Description TODO
 * @date 2020-10-09 10:29
 **/
@RestController
@RequestMapping(value = "/user")
@Slf4j
public class ContentHello {
    @GetMapping(value = "/hello1")
    public String getHello1() {
        log.info("我被调用了");
        return "Hello,this message is from user-center!";
    }
}
