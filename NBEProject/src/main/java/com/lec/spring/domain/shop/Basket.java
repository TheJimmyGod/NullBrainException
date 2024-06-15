package com.lec.spring.domain.shop;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Basket {
    private Integer id;
    private Integer user_id;
    private LocalDateTime regdate;
}
