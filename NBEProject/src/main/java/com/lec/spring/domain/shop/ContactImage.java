package com.lec.spring.domain.shop;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactImage {
    private Integer id;
    private Integer contact_id;
    private String file_name;

}
