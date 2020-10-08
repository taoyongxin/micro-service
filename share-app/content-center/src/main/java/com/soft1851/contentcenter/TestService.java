package com.soft1851.contentcenter;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Tao
 * @version 1.0
 * @ClassName TestService
 * @Description TODO
 * @date 2020-10-06 15:53
 **/
@Slf4j
@Service
public class TestService {
    // 指定sentinel的资源名称
    @SentinelResource("common")

    public String commonMethod(){
        log.info("commonMethod....");
        return "common";
    }
}
