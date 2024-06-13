package com.lec.spring.domain;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Prod {
    private Integer id;
    private String title;
    private Integer price;
    private String image;
    private String content;
    private Integer amount;
    private LocalDateTime regDate;
    private String category1;
    private String category2;
    private String category3;
    private Integer basket_id;
}
