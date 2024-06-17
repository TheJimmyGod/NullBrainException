package com.lec.spring.domain.shop;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Comment {
    private Integer id;
    private Integer user_id;
    private Integer post_id;
    private String content;
    private LocalDateTime regdate;
}
