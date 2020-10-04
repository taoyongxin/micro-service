package com.soft1851.contentcenter.configuration;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * @author Tao
 * @version 1.0
 * @ClassName SwaggerConfiguration
 * @Description TODO
 * @date 2020-10-04 20:07
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    private ApiInfo apiInfo(){
        return new ApiInfo(
                "share-app Swagger文档",
                "github地址：htpps://github.com/taoyongxin/micro-service",
                "API V1.0",
                "Terms of service",
                new Contact("陶永新","https://taoyongxin.cn","1427177855@qq.com"),
                "Apache","http://www.apache.org/", Collections.emptyList());
    }

    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.soft1851.contentcenter"))
                .build()
                .apiInfo(apiInfo());
    }
}
