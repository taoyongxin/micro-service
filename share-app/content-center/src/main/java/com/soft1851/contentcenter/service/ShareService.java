package com.soft1851.contentcenter.service;

import com.github.pagehelper.PageInfo;
import com.soft1851.contentcenter.domain.dto.ContributeDto;
import com.soft1851.contentcenter.domain.dto.ShareAuditDto;
import com.soft1851.contentcenter.domain.dto.ShareDto;
import com.soft1851.contentcenter.domain.entity.Share;

/**
 * @author Tao
 * @version 1.0
 * @ClassName ShareService
 * @Description TODO
 * @date 2020-09-29 16:35
 **/
//@Service
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public interface ShareService {

    /**
     * 获取分享详情
     *
     * @param id
     * @return
     */
    ShareDto findById(Integer id);

    /**
     * 根据标题模糊查询某个用户的分享列表数据，title为空则为所有数据，查询结果分页
     * @param title
     * @param pageNo
     * @param pageSize
     * @param userId
     * @return
     */
    PageInfo<Share> query(String title, Integer pageNo, Integer pageSize, Integer userId);


    /**
     * 投稿
     * @param contributeDto
     * @return
     */
    int insertShare(ContributeDto contributeDto);


    /**
     * 审核投稿
     * @param id
     * @param shareAuditDto
     * @return
     */
    Share auditById(Integer id, ShareAuditDto shareAuditDto);


    /**
     * 审核投稿（同步）
     * @param id
     * @param shareAuditDto
     * @return
     */
    Share auditByIdSyn(Integer id,ShareAuditDto shareAuditDto);


}
