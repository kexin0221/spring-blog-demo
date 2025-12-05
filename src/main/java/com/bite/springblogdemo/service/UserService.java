package com.bite.springblogdemo.service;

import com.bite.springblogdemo.pojo.request.UserLoginRequest;
import com.bite.springblogdemo.pojo.response.UserInfoResponse;
import com.bite.springblogdemo.pojo.response.UserLoginResponse;
import jakarta.validation.constraints.NotNull;

public interface UserService {
    UserLoginResponse checkUserInfo(UserLoginRequest userLoginRequest);

    UserInfoResponse getUserInfo(@NotNull(message = "userId不能为空") Integer userId);

    UserInfoResponse getAuthorInfo(@NotNull(message = "blogId不能为空") Integer blogId);

}
