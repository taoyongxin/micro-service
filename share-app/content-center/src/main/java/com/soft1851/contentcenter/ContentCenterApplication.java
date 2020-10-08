package com.soft1851.contentcenter;

import com.purgeteam.dispose.starter.annotation.EnableGlobalDispose;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Tao
 * @version 1.0
 * @ClassName ContentCenterApplication
 * @Description TODO
 * @date 2020-09-22 15:27
 **/
@SpringBootApplication
@MapperScan(basePackages = "com.soft1851.contentcenter.dao")
@EnableGlobalDispose
//@EnableFeignClients(defaultConfiguration = GlobalFeignConfiguration.class)
@EnableFeignClients
public class ContentCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContentCenterApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}