package com.soft1851.contentcenter.dao;

import com.soft1851.contentcenter.domain.entity.Share;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class ShareMapperTest {
    @Resource
    private ShareMapper shareMapper;

    @Test
    void get(){
        Share share = shareMapper.selectByPrimaryKey(1);
        System.out.println(share);
    }

    @Test
    void list(){
        List<Share> shares = shareMapper.selectAll();
        System.out.println(shares);
    }

}