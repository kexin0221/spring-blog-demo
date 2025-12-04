package com.bite.springblogdemo.common.interceptor;

import com.bite.springblogdemo.common.constant.Constants;
import com.bite.springblogdemo.common.util.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userToken = request.getHeader(Constants.USER_TOKEN_HEADER_KEY);
        log.info("获取token: {}", userToken);
        if (userToken == null) {
            // 拦截
            response.setStatus(401);
            return false;
        }
        // 校验token是否合法
        Claims claims = JwtUtils.parseToken(userToken);
        if (claims == null) {
            // 拦截
            response.setStatus(401);
            return false;
        }
        return true;
    }
}
