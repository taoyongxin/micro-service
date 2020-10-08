package com.soft1851.contentcenter.service;

import org.springframework.web.client.RestTemplate;

/**
 * @author Tao
 * @version 1.0
 * @ClassName SentinelTest
 * @Description TODO
 * @date 2020-10-06 15:44
 **/
public class SentinelTest {
    public static void main(String[] args) throws InterruptedException {
        RestTemplate template = new RestTemplate();
        for (int i = 0; i < 100; i++) {
            String object = template.getForObject("http://localhost:8015/test/byResource", String.class);
            System.out.println("ok");
//            Thread.sleep(200);
        }
    }
}
