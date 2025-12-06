package com.bite.springblogdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bite.springblogdemo.common.exception.BlogException;
import com.bite.springblogdemo.common.util.BeanTransUtils;
import com.bite.springblogdemo.mapper.BlogInfoMapper;
import com.bite.springblogdemo.pojo.dataobject.BlogInfo;
import com.bite.springblogdemo.pojo.request.AddBlogRequest;
import com.bite.springblogdemo.pojo.response.BlogInfoResponse;
import com.bite.springblogdemo.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogInfoMapper blogInfoMapper;

    @Override
    public List<BlogInfoResponse> getList() {
        QueryWrapper<BlogInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(BlogInfo::getDeleteFlag, 0);
        List<BlogInfo> blogInfos = blogInfoMapper.selectList(queryWrapper);
        return blogInfos.stream().map(BeanTransUtils::trans).collect(Collectors.toList());
    }

    @Override
    public BlogInfoResponse getBlogDetail(Integer blogId) {
        return BeanTransUtils.trans(getBlogInfo(blogId));
    }

    @Override
    public BlogInfo getBlogInfo(Integer blogId) {
        QueryWrapper<BlogInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(BlogInfo::getDeleteFlag, 0)
                .eq(BlogInfo::getId, blogId);
        return blogInfoMapper.selectOne(queryWrapper);
    }

    @Override
    public Boolean addBlog(AddBlogRequest addBlogRequest) {
        BlogInfo blogInfo = new BlogInfo();
        BeanUtils.copyProperties(addBlogRequest, blogInfo);
        try {
            return blogInfoMapper.insert(blogInfo) == 1;
        } catch (Exception e) {
            log.error("博客插入失败, e: ", e);
            throw new BlogException("内部错误，请联系管理员");
        }
    }
}
