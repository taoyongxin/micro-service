package com.soft1851.usercenter.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.soft1851.usercenter.domain.dto.*;
import com.soft1851.usercenter.domain.entity.User;
import com.soft1851.usercenter.service.UserService;
import com.soft1851.usercenter.util.JwtOperator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
    private final JwtOperator jwtOperator;

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

    /**
     * 增减积分接口
     *
     * @param userAddBonusDto
     * @return
     */
    @PutMapping(value = "/add-bonus")
    public User addBonus(@RequestBody UserAddBonusDto userAddBonusDto) {
        log.info("增减积分接口被请求了...");
        Integer userId = userAddBonusDto.getUserId();
        userService.addBonus(
                UserAddBonusMsgDto.builder()
                        .userId(userId)
                        .bonus(userAddBonusDto.getBonus())
                        .description("兑换分享...")
                        .event("BUY")
                        .build()
        );
        return this.userService.findById(userId);
    }


    @PostMapping(value = "/login")
    public LoginResDto login(@RequestBody LoginDto loginDto) throws WxErrorException {
        String openId;
        // 微信小程序登录，需要根据code请求openId
        if (loginDto.getOpenId() == null) {
            // 微信服务端校验是否已经登录的结果
            WxMaJscode2SessionResult result = this.wxMaService.getUserService()
                    .getSessionInfo(loginDto.getLoginCode());
            log.info(result.toString());
            // 微信的openId,用户在微信这边的唯一标识
            openId = result.getOpenid();
        } else {
            openId = loginDto.getOpenId();
        }
        // 看用户是否注册，如果没有注册就（插入），如果已经注册就返回其信息
        User user = userService.login(loginDto, openId);
        // 颁发token
        Map<String, Object> userInfo = new HashMap<>(3);
        userInfo.put("id", user.getId());
        userInfo.put("wxNickname", user.getWxNickname());
        userInfo.put("role", user.getRoles());
        String token = jwtOperator.generateToken(userInfo);

        log.info(
                "{}登录成功,生成的token = {},有效期到：{}",
                user.getWxNickname(),
                token,
                jwtOperator.getExpirationTime());

        ResponseDTO responseDTO = this.userService.checkIsSign(UserSignInDTO.builder().userId(user.getId()).build());
        int isUserSignin = 0;
        if (responseDTO.getCode()=="200"){
            isUserSignin = 0;
        }else {
            isUserSignin = 1;
        }

        return LoginResDto.builder()
                .user(
                        UserRespDto.builder()
                                .id(user.getId())
                                .avatarUrl(user.getAvatarUrl())
                                .bonus(user.getBonus())
                                .wxNickname(user.getWxNickname())
                                .build())
                .token(JwtTokenRespDto.builder()
                        .token(token)
                        .expirationTime(jwtOperator.getExpirationTime().getTime())
                        .build())
                .isUserSignin(isUserSignin)
                .build();
    }

    //    @PostMapping(value = "/wxLogin")
//    public LoginResDto codeAuth(@RequestBody WxLoginDto wxLoginDto) throws WxErrorException{
//        //通过第三方SDK获得openId
//        WxMaJscode2SessionResult result = this.wxMaService.getUserService()
//                .getSessionInfo(wxLoginDto.getCode());
//        System.out.println(result);
//        String openId = result.getOpenid();
//        LoginDto loginDto = LoginDto.builder()
//                .wxId(openId)
//                .wxNickname(wxLoginDto.getWxNickname())
//                .avatarUrl(wxLoginDto.getAvatarUrl())
//                .build();
//        User user = userService.login(loginDto);
//        return LoginResDto.builder()
//                .user(user)
//                .token("aaaabbbbcccc")
//                .build();
//    }

    /**
     * 用户签到
     * @param userSignInDTO
     * @return
     */
    @PostMapping(value = "/signin")
    public ResponseDTO signIn(@RequestBody UserSignInDTO userSignInDTO) {
        return userService.signIn(userSignInDTO);
    }

}
