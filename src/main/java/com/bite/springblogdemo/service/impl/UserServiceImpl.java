package com.bite.springblogdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bite.springblogdemo.common.exception.BlogException;
import com.bite.springblogdemo.common.util.BeanTransUtils;
import com.bite.springblogdemo.common.util.JwtUtils;
import com.bite.springblogdemo.mapper.UserInfoMapper;
import com.bite.springblogdemo.pojo.dataobject.BlogInfo;
import com.bite.springblogdemo.pojo.dataobject.UserInfo;
import com.bite.springblogdemo.pojo.request.UserLoginRequest;
import com.bite.springblogdemo.pojo.response.UserInfoResponse;
import com.bite.springblogdemo.pojo.response.UserLoginResponse;
import com.bite.springblogdemo.service.BlogService;
import com.bite.springblogdemo.service.UserService;
import jakarta.annotation.Resource;
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

    @Resource(name = "blogServiceImpl")
    private BlogService blogService;

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

    @Override
    public UserInfoResponse getUserInfo(Integer userId) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserInfo::getDeleteFlag, 0)
                .eq(UserInfo::getId, userId);
        return BeanTransUtils.trans(userInfoMapper.selectOne(queryWrapper));
    }

    @Override
    public UserInfoResponse getAuthorInfo(Integer blogId) {
        // 1.根据blogId获取博客信息(包括UserId)
        BlogInfo blogInfo = blogService.getBlogInfo(blogId);
        if (blogInfo == null || blogInfo.getUserId() <= 0) {
            throw new BlogException("博客不存在!");
        }
        // 2.根据作者ID获取作者信息
        return getUserInfo(blogInfo.getUserId());
    }
}
