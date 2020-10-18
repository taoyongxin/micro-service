package com.soft1851.contentcenter.service;

import com.github.pagehelper.PageInfo;
import com.soft1851.contentcenter.dao.ShareMapper;
import com.soft1851.contentcenter.domain.dto.ContributeDto;
import com.soft1851.contentcenter.domain.dto.ConversionDto;
import com.soft1851.contentcenter.domain.dto.ShareAuditDto;
import com.soft1851.contentcenter.domain.dto.ShareDto;
import com.soft1851.contentcenter.domain.entity.Share;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

import static com.soft1851.contentcenter.domain.enums.AuditStatusEnum.PASS;

@SpringBootTest
class ShareServiceTest {

    @Resource
    private ShareService shareService;

    @Resource
    private ShareMapper shareMapper;

    @Test
    void findById() {
        ShareDto shareDto = shareService.findById(1);
        System.out.println(shareDto);
    }

    @Test
    void findByOne() {
        Share share = this.shareMapper.selectByPrimaryKey(1);
        System.out.println(share);
    }

    @Test
    void query() {
        PageInfo<Share> query = shareService.query(null,1,10,1);
        List<Share> list = query.getList();
        list.forEach(item-> System.out.println(item.getTitle()+","+item.getDownloadUrl()));
    }

    @Test
    void insertShare() {
        ContributeDto contributeDto = ContributeDto.builder()
                .userId(1)
                .title("今天星期四")
                .isOriginal(true)
                .author("李白")
                .cover("tangshi.jpg")
                .summary("天气晴，心情好！")
                .price(20)
                .downloadUrl("www.tssbs.com")
                .build();
        shareService.insertShare(contributeDto);
    }

    @Test
    void auditById() {
        ShareAuditDto shareAuditDto = ShareAuditDto.builder()
                .auditStatusEnum(PASS)
                .reason("通过")
                .build();

        shareService.auditById(16,shareAuditDto);
    }

    @Test
    void auditByIdSyn() {
        ShareAuditDto shareAuditDto = ShareAuditDto.builder()
                .auditStatusEnum(PASS)
                .reason("通过")
                .build();
        shareService.auditByIdSyn(17,shareAuditDto);
    }

    @Test
    void getAllConversion() {
        PageInfo<ConversionDto> query = shareService.getAllConversion(1,3,1);
        List<ConversionDto> list = query.getList();
        list.forEach(item-> System.out.println(item.getTitle()+","+item.getDownloadUrl()));
    }

    @Test
    void testFindById() {
        ShareDto shareDto = shareService.findById(3);
        System.out.println(shareDto);
    }
}