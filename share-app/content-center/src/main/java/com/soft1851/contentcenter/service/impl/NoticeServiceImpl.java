package com.soft1851.contentcenter.service.impl;

import com.soft1851.contentcenter.dao.NoticeMapper;
import com.soft1851.contentcenter.domain.entity.Notice;
import com.soft1851.contentcenter.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @author Tao
 * @version 1.0
 * @ClassName NoticeServiceImpl
 * @Description TODO
 * @date 2020-10-04 19:30
 **/
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NoticeServiceImpl implements NoticeService {

    private final NoticeMapper noticeMapper;

    @Override
    public Notice getLates() {
        Example example = new Example(Notice.class);
        // 按id降序
        example.setOrderByClause("id DESC");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("showFlag", 1);
        return noticeMapper.selectByExample(example).get(0);
    }
}
