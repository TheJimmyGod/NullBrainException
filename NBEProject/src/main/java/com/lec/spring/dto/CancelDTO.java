package com.lec.spring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CancelDTO {
    private Integer amount;
    @JsonProperty("imp_uid")
    private String impId;
    @JsonProperty("merchant_uid")
    private String merchantId;
}
