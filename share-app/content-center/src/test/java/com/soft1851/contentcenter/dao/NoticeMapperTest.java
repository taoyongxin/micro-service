package com.soft1851.contentcenter.dao;

import com.soft1851.contentcenter.domain.entity.Notice;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class NoticeMapperTest {

    @Resource
    private NoticeMapper noticeMapper;

    @Test
    void list(){
        List<Notice> notices = noticeMapper.selectAll();
        System.out.println(notices);
    }
}