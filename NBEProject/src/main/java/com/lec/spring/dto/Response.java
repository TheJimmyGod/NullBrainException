package com.lec.spring.dto;

import lombok.Data;

@Data
public class Response {
    String access_token;
    String now ;
    String expired_at;
}
