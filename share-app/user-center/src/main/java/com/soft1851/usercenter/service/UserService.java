package com.soft1851.usercenter.service;

import com.soft1851.usercenter.domain.dto.LoginDto;
import com.soft1851.usercenter.domain.dto.ResponseDTO;
import com.soft1851.usercenter.domain.dto.UserAddBonusMsgDto;
import com.soft1851.usercenter.domain.dto.UserSignInDTO;
import com.soft1851.usercenter.domain.entity.User;

/**
 * @author Tao
 * @version 1.0
 * @ClassName UserService
 * @Description TODO
 * @date 2020-09-24 20:23
 **/

public interface UserService {
//
//    private final UserMapper userMapper;
//
//    public UserDto getUserDto(int id){
//        User user = userMapper.selectByPrimaryKey(id);
//        UserDto userDto = new UserDto();
//        userDto.setId(user.getId());
//        userDto.setWxId(user.getWxId());
//        userDto.setWxNickname(user.getWxNickname());
//        userDto.setAvatarUrl(user.getAvatarUrl());
//        userDto.setBonus(user.getBonus());
//        return userDto;
//    }

    /**
     * 根据用户id查询用户数据
     *
     * @param id
     * @return
     */
    User findById(Integer id);

    /**
     * 给用户增加积分
     *
     * @param userAddBonusMsgDto
     * @return
     */
    int addBonusToUser(UserAddBonusMsgDto userAddBonusMsgDto);

    /**
     * 用户登录
     * @param loginDto
     * @param openId
     * @return
     */
    User login(LoginDto loginDto,String openId);

    /**
     * 增加积分
     * @param userAddBonusMsgDto
     */
    void addBonus(UserAddBonusMsgDto userAddBonusMsgDto);


    /**
     * 用户签到
     * @param signInDTO
     * @return
     */
    ResponseDTO signIn(UserSignInDTO signInDTO);

    /**
     * 判断用户是否签到的
     * @param signInDTO
     * @return
     */
    ResponseDTO checkIsSign(UserSignInDTO signInDTO);
}
