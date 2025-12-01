package com.bite.springblogdemo.service;

import com.bite.springblogdemo.pojo.response.BlogInfoResponse;

import java.util.List;

public interface BlogService {
    List<BlogInfoResponse> getList();
}
