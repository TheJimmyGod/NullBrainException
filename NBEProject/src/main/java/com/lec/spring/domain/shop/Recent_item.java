package com.lec.spring.domain.shop;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Recent_item {

    private Integer id;
    private Integer user_id;
    private String goods_id;
    private LocalDateTime regdate;


}
