package com.bite.springblogdemo.common.exception;

import lombok.Data;

@Data
public class BlogException extends RuntimeException{
    private int code;
    private String errMsg;

    public BlogException(int code, String errMsg) {
        this.code = code;
        this.errMsg = errMsg;
    }

    public BlogException(String errMsg) {
        this.errMsg = errMsg;
    }
}
