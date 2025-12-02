package com.bite.springblogdemo.pojo.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class BlogInfoResponse {
    private Integer id;
    private String title;
    private String content;
    private Integer deleteFlag;
    @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss")
    private LocalDateTime createTime;

//    public String getContent() {
//        return content == null ? "" : content;
//    }
}
