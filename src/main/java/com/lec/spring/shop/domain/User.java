package com.lec.spring.shop.domain;

import com.lec.spring.shop.dto.OrderUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Integer id;
    private String username;
    private String password;
    private LocalDateTime regdate;
    private String name;
    private String phone;
    private String birth;
    private String email;
    private Integer gender;
    private String profileimage;
    private String grade;
    private Integer total_price;
    private Integer point;
    private String streetAddr;
    private String detailAddr;

    public OrderUser oderUser(){
        return OrderUser.builder()
                .userId(this.id)
                .username(this.username)
                .name(this.name)
                .phone(this.phone)
                .birth(this.birth)
                .email(this.email)
                .streetAddr(this.streetAddr)
                .detailAddr(this.detailAddr)
                .build();

    }

}