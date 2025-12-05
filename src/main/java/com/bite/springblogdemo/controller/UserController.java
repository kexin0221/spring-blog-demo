package com.bite.springblogdemo.controller;

import com.bite.springblogdemo.pojo.request.UserLoginRequest;
import com.bite.springblogdemo.pojo.response.UserInfoResponse;
import com.bite.springblogdemo.pojo.response.UserLoginResponse;
import com.bite.springblogdemo.service.UserService;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public UserLoginResponse login(@RequestBody @Validated UserLoginRequest userLoginRequest) {
        log.info("用户登录，用户名: {}", userLoginRequest.getUserName());
        return userService.checkUserInfo(userLoginRequest);
    }

    @RequestMapping("/getUserInfo")
    public UserInfoResponse getUserInfo(@NotNull(message = "userId不能为空") Integer userId) {
        log.info("获取用户信息, userId: {}", userId);
        return userService.getUserInfo(userId);
    }

    @RequestMapping("/getAuthorInfo")
    public UserInfoResponse getAuthorInfo(@NotNull(message = "blogId不能为空") Integer blogId) {
        log.info("获取作者信息, blogId: {}", blogId);
        return userService.getAuthorInfo(blogId);
    }
}
