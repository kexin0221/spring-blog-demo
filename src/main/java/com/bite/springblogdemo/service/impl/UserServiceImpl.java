package com.bite.springblogdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bite.springblogdemo.common.exception.BlogException;
import com.bite.springblogdemo.common.util.JwtUtils;
import com.bite.springblogdemo.mapper.UserInfoMapper;
import com.bite.springblogdemo.pojo.dataobject.UserInfo;
import com.bite.springblogdemo.pojo.request.UserLoginRequest;
import com.bite.springblogdemo.pojo.response.UserLoginResponse;
import com.bite.springblogdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserLoginResponse checkUserInfo(UserLoginRequest userLoginRequest) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserInfo::getUserName, userLoginRequest.getUserName())
                .eq(UserInfo::getDeleteFlag, 0);
        // 查询数据库
        UserInfo userInfo = userInfoMapper.selectOne(queryWrapper);

        // 用户不存在
        if (userInfo == null) {
            throw new BlogException("用户不存在");
        }
        // 密码错误
        if (!userInfo.getPassword().equals(userLoginRequest.getPassword())) {
            throw new BlogException("密码错误");
        }
        // 密码正确
        Map<String, Object> map = new HashMap<>();
        map.put("id", userInfo.getId());
        map.put("userName", userInfo.getUserName());
        return new UserLoginResponse(userInfo.getId(), JwtUtils.genToken(map));
    }
}
