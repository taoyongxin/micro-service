package com.soft1851.usercenter.controller;

import com.soft1851.usercenter.domain.dto.UserDto;
import com.soft1851.usercenter.service.UserService;
import lombok.RequiredArgsConstructor;
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
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/{id}")
    public UserDto getUserDto(@PathVariable int id){
        return userService.getUserDto(id);
    }
}
