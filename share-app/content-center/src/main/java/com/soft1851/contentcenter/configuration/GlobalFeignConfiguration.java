package com.soft1851.contentcenter.configuration;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * @author Tao
 * @version 1.0
 * @ClassName GlobalFeignConfiguration
 * @Description feign的配置类
 * 这个类不要@Configuration注解，否则必须挪到@ComponentScan能扫描的包以外
 * @date 2020-09-29 19:21
 **/
public class GlobalFeignConfiguration {
    @Bean
    public Logger.Level level(){
        // 让feign打印所有请求的细节
        return Logger.Level.FULL;
    }
}
