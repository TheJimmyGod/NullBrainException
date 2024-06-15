package com.lec.spring.domain.shop;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Review {
    private Integer id;
    private Integer user_id;
    private String goods_id;
    private LocalDateTime regdate;
    private String title;
    private String content;
    private Double rate;
}
