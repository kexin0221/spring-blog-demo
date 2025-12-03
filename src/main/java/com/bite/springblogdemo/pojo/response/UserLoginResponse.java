package com.bite.springblogdemo.pojo.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserLoginResponse {
    private Integer userId;
    private String token;
}
