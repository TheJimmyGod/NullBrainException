package com.lec.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String username;
    private String password;
    private String name;
    private String phone;
    private String birth;
    private String email;
    private Integer gender;




    private Integer id;
    final private Boolean is_default = true;
    private String street_addr;
    private String detail_addr;
    private String addressName;
}
