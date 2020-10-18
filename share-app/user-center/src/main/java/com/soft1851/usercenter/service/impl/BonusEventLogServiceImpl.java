package com.soft1851.usercenter.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.soft1851.usercenter.dao.BonusEventLogMapper;
import com.soft1851.usercenter.domain.entity.BonusEventLog;
import com.soft1851.usercenter.service.BonusEventLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author Tao
 * @version 1.0
 * @ClassName BonusEventLogServiceImpl
 * @Description TODO
 * @date 2020-10-15 11:23
 **/
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BonusEventLogServiceImpl implements BonusEventLogService {
    private final BonusEventLogMapper bonusEventLogMapper;


    @Override
    public PageInfo<BonusEventLog> getAllBonusEventLog(Integer pageNo, Integer pageSize, Integer userId) {
        // 启动分页
        PageHelper.startPage(pageNo, pageSize);
        // 构造查询实例
        Example example = new Example(BonusEventLog.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        List<BonusEventLog> bonusEventLogs = this.bonusEventLogMapper.selectByExample(example);
        return new PageInfo<>(bonusEventLogs);
    }
}
