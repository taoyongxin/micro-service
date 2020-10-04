package com.soft1851.contentcenter.service;

import com.github.pagehelper.PageInfo;
import com.soft1851.contentcenter.domain.dto.ShareDto;
import com.soft1851.contentcenter.domain.entity.Share;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class ShareServiceTest {

    @Resource
    private ShareService shareService;

    @Test
    void findById() {
        ShareDto shareDto = shareService.findById(1);
        System.out.println(shareDto);
    }

    @Test
    void query() {
        PageInfo<Share> query = shareService.query(null,1,10,1);
        List<Share> list = query.getList();
        list.forEach(item-> System.out.println(item.getTitle()+","+item.getDownloadUrl()));
    }

//    @Test
//    void findAll() {
//        List<ShareDto> shareDtoList = shareService.findAll();
//        System.out.println(shareDtoList);
//    }
}