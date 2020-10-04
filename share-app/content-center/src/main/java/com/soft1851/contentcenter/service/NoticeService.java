package com.soft1851.contentcenter.service;

import com.soft1851.contentcenter.domain.entity.Notice;

/**
 * @author Tao
 */
public interface NoticeService {
    /**
     * 查询最新公告
     * @return
     */
    Notice getLates();
}
