package com.lec.spring.domain.shop;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Contact {
    private Integer id;
    private Integer user_id;
    private Integer goods_id;

    private LocalDateTime regdate;
    private String title;
    private String content;
    private String status;
    private String answer;
}
