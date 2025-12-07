package com.bite.springblogdemo.pojo.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateBlogRequest {
    @NotNull(message = "博客id不能为空")
    private Integer id;

    @NotBlank(message = "标题不能为空")
    private String title;

    private String content;
}
