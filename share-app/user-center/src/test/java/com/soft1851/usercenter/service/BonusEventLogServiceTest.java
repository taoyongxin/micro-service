package com.soft1851.usercenter.service;

import com.github.pagehelper.PageInfo;
import com.soft1851.usercenter.domain.entity.BonusEventLog;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class BonusEventLogServiceTest {

    @Resource
    private BonusEventLogService bonusEventLogService;

    @Test
    void getAllBonusEventLog() {
        PageInfo<BonusEventLog> query = bonusEventLogService.getAllBonusEventLog(1,3,3);
        List<BonusEventLog> list = query.getList();
        list.forEach(item-> System.out.println(item.getDescription()+","+item.getEvent()));
    }
}