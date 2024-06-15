package com.lec.spring.domain.shop;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment {
    private Integer id;
    private Integer user_id;
    private Integer post_id;
    private String content;
    private LocalDateTime regdate;
}
