package com.lec.spring.domain.shop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostImage {
    private Integer id;
    private String file_name;
    private Integer post_id;
}
