package com.bite.springblogdemo.common.advice;

import com.bite.springblogdemo.common.exception.BlogException;
import com.bite.springblogdemo.pojo.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ResponseBody
@ControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler
    public Result exceptionHandler(Exception e) {
        log.error("发生异常, e: ", e);
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler
    public Result exceptionHandler(BlogException e) {
        log.error("发生异常, e: ", e);
        return Result.fail(e.getMessage());
    }
}
