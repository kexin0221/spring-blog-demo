package com.bite.springblogdemo.controller;

import com.bite.springblogdemo.pojo.request.AddBlogRequest;
import com.bite.springblogdemo.pojo.response.BlogInfoResponse;
import com.bite.springblogdemo.pojo.response.Result;
import com.bite.springblogdemo.service.BlogService;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("/blog")
@RestController
public class BlogController {
    @Resource(name = "blogServiceImpl")
    private BlogService blogService;

    @RequestMapping("/getList")
    public List<BlogInfoResponse> getList() {
        log.info("查询博客列表...");
        return blogService.getList();
    }

    @RequestMapping("/getBlogDetail")
    public BlogInfoResponse getBlogDetail(@NotNull Integer blogId) {
        log.info("获取博客详情...");
        return blogService.getBlogDetail(blogId);
    }

    /**
     * 添加博客
     * @param addBlogRequest 博客信息
     * @return true-成功  false-失败
     */
    @RequestMapping("/add")
    public Boolean addBlog(@RequestBody @Validated AddBlogRequest addBlogRequest) {
        log.info("添加博客, userId: {} title: {}", addBlogRequest.getUserId(), addBlogRequest.getTitle());
        return blogService.addBlog(addBlogRequest);
    }
}
