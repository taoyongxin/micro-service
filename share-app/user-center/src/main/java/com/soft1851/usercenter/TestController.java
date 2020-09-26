package com.soft1851.usercenter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Tao
 * @version 1.0
 * @ClassName TestController
 * @Description TODO
 * @date 2020-09-23 8:35
 **/
@Slf4j
@RestController
@RequestMapping(value = "test")
public class TestController {
    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 测试：服务发现，证明内容中心总能找到用户中心
     * @return
     */
    @GetMapping("/discovery")
    public List<ServiceInstance> getInstances(){
        return this.discoveryClient.getInstances("user-center");
    }
}
