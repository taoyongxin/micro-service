package com.soft1851.contentcenter.dao;

import com.soft1851.contentcenter.domain.entity.MidUserShare;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class MidUserShareMapperTest {

    @Resource
    private MidUserShareMapper midUserShareMapper;

    @Test
    void getMidUserShare() {
        Example example = new Example(MidUserShare.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",1);
        List<MidUserShare> midUserShares = this.midUserShareMapper.selectByExample(example);
        System.out.println(midUserShares);
    }
}