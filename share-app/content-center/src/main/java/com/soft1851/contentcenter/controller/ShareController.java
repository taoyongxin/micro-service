package com.soft1851.contentcenter.controller;

import com.soft1851.contentcenter.domain.dto.ContributeDto;
import com.soft1851.contentcenter.domain.dto.ShareDto;
import com.soft1851.contentcenter.domain.entity.Share;
import com.soft1851.contentcenter.service.ShareService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Tao
 * @version 1.0
 * @ClassName ShareController
 * @Description TODO
 * @date 2020-09-29 16:40
 **/
@RestController
@RequestMapping(value = "/shares")
@Api(tags = "分享接口", value = "提供分享相关的Rest API")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareController {
    private final ShareService shareService;

//    @GetMapping(value = "/all")
//    public ResponseResult getAll() {
//        List<Share> shares = shareService.findAll();
//        List<ShareDto> shareDtoList = new ArrayList<>();
//        shares.forEach(share -> {
//            int userId = share.getUserId();
//            UserDto userDto = restTemplate.getForObject("http://user-center/user/{id}", UserDto.class, userId);
//            assert userDto != null;
//            ShareDto shareDto = ShareDto.builder()
//                    .auditShare(share.getAuditStatus())
//                    .author(share.getAuthor())
//                    .buyCount(share.getBuyCount())
//                    .cover(share.getCover())
//                    .createTime(share.getCreateTime())
//                    .downloadUrl(share.getDownloadUrl())
//                    .id(share.getId())
//                    .isOriginal(share.getIsOriginal())
//                    .price(share.getPrice())
//                    .reason(share.getReason())
//                    .show(share.getShowFlag())
//                    .summary(share.getSummary())
//                    .title(share.getTitle())
//                    .updateTime(share.getUpdateTime())
//                    .wxId(userDto.getWxId())
//                    .wxNickname(userDto.getWxNickname())
//                    .avatarUrl(userDto.getAvatarUrl())
//                    .bonus(userDto.getBonus())
//                    .build();
//            shareDtoList.add(shareDto);
//        });
//        return new ResponseResult(200,"请求成功",shareDtoList);
//    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "查询指定id的分享详情",notes = "查询指定id的分享详情")
    public ShareDto findById(@PathVariable Integer id) {
        return this.shareService.findById(id);
    }

//    @GetMapping(value = "/all")
//    public List<ShareDto> getAll(){
//        return this.shareService.findAll();
//    }

    @GetMapping("/query")
    @ApiOperation(value = "分享列表",notes = "分享列表")
    public List<Share> query(
            @RequestParam(required = false) String title,
            @RequestParam(required = false, defaultValue = "1") Integer pageNo,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) Integer userId) throws Exception {
        if (pageSize > 100) {
            pageSize = 100;
        }
        return this.shareService.query(title,pageNo,pageSize,userId).getList();
    }

    @PostMapping(value = "/insert")
    @ApiOperation(value = "投稿",notes = "用户投稿")
    public int insertShare(@RequestBody ContributeDto contributeDto){
        return this.shareService.insertShare(contributeDto);
    }
}
