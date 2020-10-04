package com.soft1851.contentcenter.configuration;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * @author Tao
 * @version 1.0
 * @ClassName UserCenterFeignConfiguration
 * @Description 自定义配置Feign的日志的级别
 * @date 2020-09-30 10:18
 **/
public class UserCenterFeignConfiguration {
    @Bean
    public Logger.Level level(){
        // 让feign打印所有请求的细节
        return Logger.Level.FULL;
    }
}
