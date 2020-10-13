package com.soft1851.usercenter.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.soft1851.usercenter.domain.dto.LoginDto;
import com.soft1851.usercenter.domain.dto.LoginResDto;
import com.soft1851.usercenter.domain.dto.UserAddBonusMsgDto;
import com.soft1851.usercenter.domain.dto.WxLoginDto;
import com.soft1851.usercenter.domain.entity.User;
import com.soft1851.usercenter.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Tao
 * @version 1.0
 * @ClassName UserController
 * @Description TODO
 * @date 2020-09-24 20:24
 **/
@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserController {
    private final UserService userService;
    private final WxMaService wxMaService;

    @GetMapping(value = "/{id}")
    public User findUserById(@PathVariable Integer id) {
        log.info("我被请求了");
        return this.userService.findById(id);
    }

    @GetMapping("/q")
    public User query(User user) {
        return user;
    }

    /**
     * 给用户增加积分（同步）
     *
     * @param userAddBonusMsgDto
     * @return
     */
    @PutMapping(value = "/add/bonus")
    public Integer addBonusToUser(@RequestBody UserAddBonusMsgDto userAddBonusMsgDto) {
        return this.userService.addBonusToUser(userAddBonusMsgDto);
    }

    @PostMapping(value = "/login")
    public LoginResDto getToken(@RequestBody LoginDto loginDto) {
        System.out.println(loginDto);
        User user = userService.login(loginDto);
        return LoginResDto.builder()
                .user(user)
                .token("aaaabbbbcccc")
                .build();
    }

    @PostMapping(value = "/wxLogin")
    public LoginResDto codeAuth(@RequestBody WxLoginDto wxLoginDto) throws WxErrorException{
        //通过第三方SDK获得openId
        WxMaJscode2SessionResult result = this.wxMaService.getUserService()
                .getSessionInfo(wxLoginDto.getCode());
        System.out.println(result);
        String openId = result.getOpenid();
        LoginDto loginDto = LoginDto.builder()
                .wxId(openId)
                .wxNickname(wxLoginDto.getWxNickname())
                .avatarUrl(wxLoginDto.getAvatarUrl())
                .build();
        User user = userService.login(loginDto);
        return LoginResDto.builder()
                .user(user)
                .token("aaaabbbbcccc")
                .build();
    }


}
