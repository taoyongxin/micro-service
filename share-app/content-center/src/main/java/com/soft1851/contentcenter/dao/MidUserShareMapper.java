package com.soft1851.contentcenter.dao;

import com.soft1851.contentcenter.domain.entity.MidUserShare;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author Tao
 */
public interface MidUserShareMapper extends Mapper<MidUserShare> {
    /**
     * 查询某个用户的可兑换的列表数据
     * @param userId
     * @return
     */
    @Select("SELECT * FROM mid_user_share WHERE user_id = #{userId}")
    List<MidUserShare> getMidUserShare(int userId);
}
