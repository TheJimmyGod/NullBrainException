package com.lec.spring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CancelResponse{
    private Integer code;
    private String message;
    @JsonProperty("response")
    private CancelDTO cancelDTO;
}
