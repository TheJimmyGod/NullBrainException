package com.lec.spring.domain.shop;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
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

    private Address address;

}
