package com.soft1851.contentcenter.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.soft1851.contentcenter.dao.MidUserShareMapper;
import com.soft1851.contentcenter.dao.ShareMapper;
import com.soft1851.contentcenter.domain.dto.ShareDto;
import com.soft1851.contentcenter.domain.dto.UserDto;
import com.soft1851.contentcenter.domain.entity.MidUserShare;
import com.soft1851.contentcenter.domain.entity.Share;
import com.soft1851.contentcenter.feignclient.UserCenterFeignClient;
import com.soft1851.contentcenter.service.ShareService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Tao
 * @version 1.0
 * @ClassName ShareServiceImpl
 * @Description TODO
 * @date 2020-09-29 19:12
 **/
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareServiceImpl implements ShareService {
    private final ShareMapper shareMapper;

    private final UserCenterFeignClient userCenterFeignClient;
    private final MidUserShareMapper midUserShareMapper;


    @Override
    public ShareDto findById(Integer id) {
        // 获取分享实体
        Share share = this.shareMapper.selectByPrimaryKey(id);
        // 获得发布人id
        Integer userId = share.getUserId();

        // 1. 代码不可读
        // 2. 复杂的url难以维护：https://user-center/s?ie={ie}&f={f}&rsv_bp=1&rsv_idx=1&tn=baidu&wd=a&rsv_pq=c86459bd002cfbaa&rsv_t=edb19hb%2BvO%2BTySu8dtmbl%2F9dCK%2FIgdyUX%2BxuFYuE0G08aHH5FkeP3n3BXxw&rqlang=cn&rsv_enter=1&rsv_sug3=1&rsv_sug2=0&inputT=611&rsv_sug4=611
        // 3. 难以相应需求的变化，变化很没有幸福感
        // 4. 编程体验不统一
        UserDto userDTO = this.userCenterFeignClient.findUserById(userId);

        ShareDto shareDTO = new ShareDto();
        // 属性的装配
        BeanUtils.copyProperties(share, shareDTO);
        shareDTO.setWxNickname(userDTO.getWxNickname());
        return shareDTO;
    }

    @Override
    public PageInfo<Share> query(String title, Integer pageNo, Integer pageSize, Integer userId) {
        // 启动分页
        PageHelper.startPage(pageNo, pageSize);
        // 构造查询实例
        Example example = new Example(Share.class);
        Example.Criteria criteria = example.createCriteria();
        // 如标题关键字不空，则加上模糊查询条件，否则结果即所有数据
        if (StringUtil.isNotEmpty(title)) {
            criteria.andLike("title", "%" + title + "%");
        }
        // 执行按条件查询
        List<Share> shares = this.shareMapper.selectByExample(example);
        // 处理后的Share数据列表
        List<Share> sharesDeal;
        // 1.如果用户未登录，那么downloadUrl全部设为null
        if (userId == null) {
            sharesDeal = shares.stream()
                    .peek(share -> {
                        share.setDownloadUrl(null);
                    })
                    .collect(Collectors.toList());
        }
        // 2.如果用户登录了，那么查询一下mid_user_share，如果没有数据，那么这条share的downloadUrl也设为null
        // 只有自己分享的资源才能直接看到下载链接，否则显示“兑换”
        else {
            sharesDeal = shares.stream()
                    .peek(share -> {
                        MidUserShare midUserShare = this.midUserShareMapper.selectOne(
                                MidUserShare.builder()
                                        .userId(userId)
                                        .shareId(share.getId())
                                        .build()
                        );
                        if (midUserShare == null) {
                            share.setDownloadUrl(null);
                        }
                    })
                    .collect(Collectors.toList());
        }
        return new PageInfo<>(sharesDeal);
    }

//    @Override
//    public List<ShareDto> findAll() {
//        List<Share> shares = shareMapper.selectAll();
//        List<ShareDto> shareDtoList = new ArrayList<>();
//        shares.forEach(share -> {
//            int userId = share.getUserId();
//            UserDto userDto = this.userCenterFeignClient.findUserById(userId);
//            assert userDto != null;
//            ShareDto shareDto = ShareDto.builder()
//                    .id(share.getId())
//                    .userId(userId)
//                    .title(share.getTitle())
////                    .createTime(share.getCreateTime())
////                    .updateTime(share.getUpdateTime())
//                    .isOriginal(share.getIsOriginal())
//                    .author(share.getAuthor())
//                    .cover(share.getCover())
//                    .summary(share.getSummary())
//                    .price(share.getPrice())
//                    .downloadUrl(share.getDownloadUrl())
//                    .buyCount(share.getBuyCount())
//                    .showFlag(share.getShowFlag())
//                    .auditStatus(share.getAuditStatus())
//                    .reason(share.getReason())
//                    .wxNickname(userDto.getWxNickname())
//                    .build();
//            shareDtoList.add(shareDto);
//        });
//        return shareDtoList;
//    }
}
