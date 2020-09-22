package com.soft1851.contentcenter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tao
 * @version 1.0
 * @ClassName ContentHelloController
 * @Description TODO
 * @date 2020-09-22 15:28
 **/
@RestController
@RequestMapping(value = "/content")
public class ContentHelloController {
    @GetMapping
    public String getHello(){
        return "hello content-center!";
    }
}
