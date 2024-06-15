package com.lec.spring.domain.shop;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Purchase {
    private Integer id;
    private Integer user_id;
    private LocalDateTime regdate;
    private String street_addr;
    private String detail_address;
    private String phone;
    private String request;
}
