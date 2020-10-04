package com.soft1851.usercenter.controller;

import com.soft1851.usercenter.domain.entity.User;
import com.soft1851.usercenter.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
//
//    @GetMapping(value = "/{id}")
//    public UserDto getUserDto(@PathVariable int id){
//        return userService.getUserDto(id);
//    }

    @GetMapping(value = "/{id}")
    public User findUserById(@PathVariable Integer id){
        log.info("我被请求了");
        return this.userService.findById(id);
    }

    @GetMapping("/q")
    public User query(User user){
        return user;
    }
}
