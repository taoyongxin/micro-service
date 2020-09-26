package com.soft1851.contentcenter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Tao
 * @version 1.0
 * @ClassName TestController
 * @Description TODO
 * @date 2020-09-23 8:33
 **/
@Slf4j
@RestController
@RequestMapping(value = "test")
public class TestController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;


    /**
     * 测试服务发现，证明内容中心总能找到用户中心
     *
     * @return
     */
    @GetMapping("/discovery")
    public List<ServiceInstance> getInstances() {
        return this.discoveryClient.getInstances("content-center");
    }

    @GetMapping("/call/hello")
    public String callUserCenter() {
        //用户中心所有的实例信息
        List<ServiceInstance> instances = discoveryClient.getInstances("user-center");
        //stream编程、Lambda表达式、函数表达式
//        String targetUrl = instances.stream()
//                .map(instance -> instance.getUri().toString() + "/user/hello")
//                .findFirst()
//                .orElseThrow(()->new IllegalArgumentException("当前没有实例！"));


        String targetUrl = instances.get(instances.size()).getUri() + "/user/hello";
        System.out.println(targetUrl);
        log.info("请求的目标地址: {}", targetUrl);
        return restTemplate.getForObject(targetUrl, String.class);

    }

    @GetMapping(value = "/call/ribbon")
    public String callByRibbon() {
        return restTemplate.getForObject("http://user-center/user/hello", String.class);
    }

}
