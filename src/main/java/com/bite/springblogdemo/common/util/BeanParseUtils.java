package com.bite.springblogdemo.common.util;

import com.bite.springblogdemo.pojo.dataobject.BlogInfo;
import com.bite.springblogdemo.pojo.response.BlogInfoResponse;
import org.springframework.beans.BeanUtils;

public class BeanParseUtils {
    public static BlogInfoResponse trans(BlogInfo blogInfo) {
        if (blogInfo == null) {
            return null;
        }
        BlogInfoResponse response = new BlogInfoResponse();
        BeanUtils.copyProperties(blogInfo, response);
        return response;
    }
}
