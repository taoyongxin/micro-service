package com.soft1851.usercenter.service;

import com.github.pagehelper.PageInfo;
import com.soft1851.usercenter.domain.entity.BonusEventLog;

/**
 * @author Tao
 */
public interface BonusEventLogService {
    /**
     * 获取个人积分记录
     * @param pageNo
     * @param pageSize
     * @param userId
     * @return
     */
    PageInfo<BonusEventLog> getAllBonusEventLog(Integer pageNo,Integer pageSize,Integer userId);

}
