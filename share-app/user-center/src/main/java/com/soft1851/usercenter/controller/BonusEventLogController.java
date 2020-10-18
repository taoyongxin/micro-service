package com.soft1851.usercenter.controller;

import com.soft1851.usercenter.domain.entity.BonusEventLog;
import com.soft1851.usercenter.service.BonusEventLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Tao
 * @version 1.0
 * @ClassName BonusEventLogController
 * @Description TODO
 * @date 2020-10-15 11:32
 **/
@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class BonusEventLogController {
    private final BonusEventLogService bonusEventLogService;

    @GetMapping("/log")
    public List<BonusEventLog> getAllBonusEventLog(
            @RequestParam(required = false, defaultValue = "1") Integer pageNo,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam() Integer userId) {
        if (pageSize > 100) {
            pageSize = 100;
        }
        return this.bonusEventLogService.getAllBonusEventLog(pageNo, pageSize, userId).getList();
    }
}
