package com.soft1851.contentcenter.controller;

import com.soft1851.contentcenter.domain.dto.ContributeDto;
import com.soft1851.contentcenter.domain.dto.FindOneDto;
import com.soft1851.contentcenter.domain.dto.ShareDto;
import com.soft1851.contentcenter.domain.entity.Share;
import com.soft1851.contentcenter.service.ShareService;
import com.soft1851.contentcenter.util.JwtOperator;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.List;

/**
 * @author Tao
 * @version 1.0
 * @ClassName ShareController
 * @Description TODO
 * @date 2020-09-29 16:40
 **/
@Slf4j
@RestController
@RequestMapping(value = "/shares")
@Api(tags = "分享接口", value = "提供分享相关的Rest API")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareController {
    private final ShareService shareService;
    private final JwtOperator jwtOperator;

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

    @PostMapping(value = "/id")
    @ApiOperation(value = "查询指定id的分享详情", notes = "查询指定id的分享详情")
    public ShareDto findById(@RequestBody FindOneDto findOneDto) {
        return this.shareService.findById(findOneDto.getId());
    }

//    @GetMapping(value = "/all")
//    public List<ShareDto> getAll(){
//        return this.shareService.findAll();
//    }

    @GetMapping("/query")
    @ApiOperation(value = "分享列表", notes = "分享列表")
    public List<Share> query(
            @RequestParam(required = false) String title,
            @RequestParam(required = false, defaultValue = "1") Integer pageNo,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestHeader(value = "X-Token", required = false) String token) {
        if (pageSize > 100) {
            pageSize = 100;
        }
        Integer userId = null;
        if (StringUtils.isNotBlank(token)) {
            Claims claims = this.jwtOperator.getClaimsFromToken(token);
            log.info(claims.toString());
            System.out.println("*********" + claims.toString());
            userId = (Integer) claims.get("id");
        } else {
            log.info("没有token");
            System.out.println("未找到token");
        }
        return this.shareService.query(title, pageNo, pageSize, userId).getList();
    }

    @PostMapping(value = "/insert")
    @ApiOperation(value = "投稿", notes = "用户投稿")
    public int insertShare(@RequestBody ContributeDto contributeDto) {
        return this.shareService.insertShare(contributeDto);
    }

    private final AsyncRestTemplate asyncRestTemplate;

    @GetMapping(value = "/sayHelloAys")
    public String sayHelloAys() {
        //异步发送
        ListenableFuture<ResponseEntity<String>> entity = asyncRestTemplate.getForEntity("http://user-center/user/hello", String.class);
        entity.addCallback(result -> log.info(result.getBody()), (e) -> log.error(e.getMessage()));
        return entity.toString();
    }


}
