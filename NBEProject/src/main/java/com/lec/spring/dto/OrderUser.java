package com.lec.spring.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderUser {
    private Integer userId;
    private String username;
    private String name;
    private String phone;
    private String birth;
    private String email;
    private String streetAddr;
    private String detailAddr;
}
