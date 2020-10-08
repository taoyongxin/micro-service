package com.soft1851.contentcenter;

import com.purgeteam.dispose.starter.annotation.EnableGlobalDispose;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.AsyncRestTemplate;
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


    @Bean
    @LoadBalanced
    public AsyncRestTemplate asyncRestTemplate(){
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        //设置链接超时时间
        factory.setConnectTimeout(100);
        //设置读取资料超时时间
        factory.setReadTimeout(200);
        //设置异步任务（线程不会重用，每次调用时都会重新启动一个新的线程
        factory.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return new AsyncRestTemplate(factory);
    }
}