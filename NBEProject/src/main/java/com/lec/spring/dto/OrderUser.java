package com.lec.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
