package com.soft1851.usercenter.service;

import com.soft1851.usercenter.domain.dto.UserAddBonusMsgDto;
import com.soft1851.usercenter.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class UserServiceTest {
    @Resource
    private UserService userService;

    @Test
    void findById() {
        User user = userService.findById(1);
        System.out.println(user);
    }

    @Test
    void addBonusToUser() {
        UserAddBonusMsgDto userAddBonusMsgDto = UserAddBonusMsgDto.builder()
                .userId(1)
                .bonus(100)
                .build();
        System.out.println(userService.addBonusToUser(userAddBonusMsgDto));
    }

}