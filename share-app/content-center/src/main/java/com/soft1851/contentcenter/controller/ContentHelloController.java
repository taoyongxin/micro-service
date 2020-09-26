package com.soft1851.contentcenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/call")
    public String getHello() {
        return restTemplate.getForObject("http://localhost:8011/user/hello",String.class);
    }
}
