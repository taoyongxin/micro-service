package com.soft1851.coursecenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Tao
 * @version 1.0
 * @ClassName CourseCenterApplication
 * @Description TODO
 * @date 2020-09-19 21:15
 **/

@SpringBootApplication
@MapperScan(basePackages = "com.soft1851.coursecenter.dao")
public class CourseCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseCenterApplication.class, args);
    }
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}