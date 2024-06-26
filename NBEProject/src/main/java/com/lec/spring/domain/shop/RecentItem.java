package com.lec.spring.domain.shop;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RecentItem {

    private Integer id;
    private Integer user_id;
    private String goods_id;
    private LocalDateTime regdate;


}
