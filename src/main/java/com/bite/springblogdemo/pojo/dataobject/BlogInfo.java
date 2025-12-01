package com.bite.springblogdemo.pojo.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class BlogInfo {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private String content;
    private Integer userId;
    private Integer deleteFlag;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
