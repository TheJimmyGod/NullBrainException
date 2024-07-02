package com.lec.spring.dto;

import lombok.Data;

@Data
public class Token {
    private Integer code;
    private String message;
    private Response response;
}
