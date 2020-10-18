package com.soft1851.usercenter.service.impl;

import com.soft1851.usercenter.dao.BonusEventLogMapper;
import com.soft1851.usercenter.dao.UserMapper;
import com.soft1851.usercenter.domain.dto.LoginDto;
import com.soft1851.usercenter.domain.dto.ResponseDTO;
import com.soft1851.usercenter.domain.dto.UserAddBonusMsgDto;
import com.soft1851.usercenter.domain.dto.UserSignInDTO;
import com.soft1851.usercenter.domain.entity.BonusEventLog;
import com.soft1851.usercenter.domain.entity.User;
import com.soft1851.usercenter.service.UserService;
import com.soft1851.usercenter.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author Tao
 * @version 1.0
 * @ClassName UserServiceImpl
 * @Description TODO
 * @date 2020-09-29 18:52
 **/
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    private final BonusEventLogMapper bonusEventLogMapper;

    @Override
    public User findById(Integer id) {
        return this.userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int addBonusToUser(UserAddBonusMsgDto userAddBonusMsgDto) {
        // 查找用户
        Integer userId = userAddBonusMsgDto.getUserId();
        User user = this.userMapper.selectByPrimaryKey(userId);
        // 增加积分
        user.setBonus(user.getBonus() + userAddBonusMsgDto.getBonus());
        String des = "投稿加积分";
        if (userAddBonusMsgDto.getBonus() < 0) {
            des = "兑换减积分";
        }
        this.bonusEventLogMapper.insert(
                BonusEventLog.builder()
                        .userId(userId)
                        .value(userAddBonusMsgDto.getBonus())
                        .event("CONTRIBUTE")
                        .createTime(new Date())
                        .description(des)
                        .build()
        );
        return this.userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public User login(LoginDto loginDto, String openId) {
        // 先根据openId查找用户
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("wxId", openId);
        List<User> users = this.userMapper.selectByExample(example);
        // 没找到，是新用户直接注册
        if (users.size() == 0) {
            User saveUser = User.builder()
                    .wxId(openId)
                    .avatarUrl(loginDto.getAvatarUrl())
                    .wxNickname(loginDto.getWxNickname())
                    .roles("user")
                    .bonus(100)
                    .createTime(Timestamp.valueOf(LocalDateTime.now()))
                    .updateTime(Timestamp.valueOf(LocalDateTime.now()))
                    .build();
            this.userMapper.insertSelective(saveUser);
            return saveUser;
        }
        return users.get(0);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addBonus(UserAddBonusMsgDto userAddBonusMsgDto) {
        System.out.println(userAddBonusMsgDto);

        // 1. 为用户加积分
        Integer userId = userAddBonusMsgDto.getUserId();
        Integer bonus = userAddBonusMsgDto.getBonus();
        User user = this.userMapper.selectByPrimaryKey(userId);

        user.setBonus(user.getBonus() + bonus);
        this.userMapper.updateByPrimaryKeySelective(user);
        // 2. 记录日志到bonus_event_log表里面
        this.bonusEventLogMapper.insert(
                BonusEventLog.builder()
                        .userId(userId)
                        .value(bonus)
                        .event(userAddBonusMsgDto.getEvent())
                        .createTime(new Date())
                        .description(userAddBonusMsgDto.getDescription())
                        .build()
        );
        log.info("积分添加完毕...");

    }

    @Override
    public ResponseDTO signIn(UserSignInDTO signInDTO) {
        User user = this.userMapper.selectByPrimaryKey(signInDTO.getUserId());
        if (user == null) {
            throw new IllegalArgumentException("该用户不存在!");
        }
        Example example = new Example(BonusEventLog.class);
        Example.Criteria criteria = example.createCriteria();
        example.setOrderByClause("id DESC");
        criteria.andEqualTo("userId", signInDTO.getUserId());
        criteria.andEqualTo("event", "SIGN_IN");
        List<BonusEventLog> bonusEventLog = this.bonusEventLogMapper.selectByExample(example);
        if (bonusEventLog.size() != 0) {
            BonusEventLog bonusEventLog1 = bonusEventLog.get(0);
            Date date = bonusEventLog1.getCreateTime();
            try {
                if (DateUtil.checkAllotSigin(date) == 0) {
                    this.bonusEventLogMapper.insert(BonusEventLog.builder()
                            .userId(signInDTO.getUserId())
                            .event("SIGN_IN")
                            .value(20)
                            .description("签到加积分")
                            .createTime(new Date())
                            .build());
                    // 查询用户
                    User user1 = userMapper.selectByPrimaryKey(signInDTO.getUserId());
                    //增加加分
                    user1.setBonus(user1.getBonus()+20);
                    this.userMapper.updateByPrimaryKeySelective(user1);

                    return new ResponseDTO(true, "200", "签到成功", user.getWxNickname() + "用户签到成功", 1l);
                } else if (DateUtil.checkAllotSigin(date) == 1) {
                    return new ResponseDTO(false, "201", "签到失败", user.getWxNickname() + "今天已经签到过了", 1l);
                } else if (DateUtil.checkAllotSigin(date) == 2) {
                    return new ResponseDTO(false, "202", "签到失败", user.getWxNickname() + "用户，今天数据错乱了", 1l);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            this.bonusEventLogMapper.insert(BonusEventLog.builder()
                    .userId(signInDTO.getUserId())
                    .event("SIGN_IN")
                    .value(20)
                    .description("签到加积分")
                    .createTime(new Date())
                    .build());
            // 查询用户
            User user1 = userMapper.selectByPrimaryKey(signInDTO.getUserId());
            //增加加分
            user1.setBonus(user1.getBonus()+20);
            this.userMapper.updateByPrimaryKeySelective(user1);
            return new ResponseDTO(true, "200", "签到成功", user.getWxNickname() + "用户签到成功", 1l);
        }
        return new ResponseDTO(true, "200", "签到成功", user.getWxNickname() + "签到成功", 1l);
    }

    @Override
    public ResponseDTO checkIsSign(UserSignInDTO signInDTO) {
        User user = this.userMapper.selectByPrimaryKey(signInDTO.getUserId());
        if (user == null) {
            throw new IllegalArgumentException("该用户不存在!");
        }
        Example example = new Example(BonusEventLog.class);
        Example.Criteria criteria = example.createCriteria();
        example.setOrderByClause("id DESC");
        criteria.andEqualTo("userId", signInDTO.getUserId());
        criteria.andEqualTo("event", "SIGN_IN");
        List<BonusEventLog> bonusEventLog = this.bonusEventLogMapper.selectByExample(example);
        if (bonusEventLog.size() != 0) {
            BonusEventLog bonusEventLog1 = bonusEventLog.get(0);
            Date date = bonusEventLog1.getCreateTime();
            try {
                if (DateUtil.checkAllotSigin(date) == 0) {
                    return new ResponseDTO(true, "200", "该用户还没有签到", "可以签到", 1l);
                } else if (DateUtil.checkAllotSigin(date) == 1) {
                    return new ResponseDTO(false, "201", "已经签到了", "不可以签到", 1l);
                } else if (DateUtil.checkAllotSigin(date) == 2) {
                    return new ResponseDTO(false, "202", "数据出错了", "不可以签到", 1l);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ResponseDTO(true, "200", "该用户还没有签到", "可以签到", 1l);

    }

}
