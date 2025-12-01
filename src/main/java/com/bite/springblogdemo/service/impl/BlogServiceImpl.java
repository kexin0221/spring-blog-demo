package com.bite.springblogdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bite.springblogdemo.mapper.BlogInfoMapper;
import com.bite.springblogdemo.pojo.dataobject.BlogInfo;
import com.bite.springblogdemo.pojo.response.BlogInfoResponse;
import com.bite.springblogdemo.service.BlogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogInfoMapper blogInfoMapper;

    @Override
    public List<BlogInfoResponse> getList() {
        QueryWrapper<BlogInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(BlogInfo::getDeleteFlag, 0);
        List<BlogInfo> blogInfos = blogInfoMapper.selectList(queryWrapper);
        return blogInfos.stream().map(x -> {
            BlogInfoResponse response = new BlogInfoResponse();
            BeanUtils.copyProperties(x, response);
            return response;
        }).collect(Collectors.toList());
    }
}
