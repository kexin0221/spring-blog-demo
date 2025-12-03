package com.bite.springblogdemo.service;

import com.bite.springblogdemo.pojo.request.UserLoginRequest;
import com.bite.springblogdemo.pojo.response.UserLoginResponse;

public interface UserService {
    UserLoginResponse checkUserInfo(UserLoginRequest userLoginRequest);
}
