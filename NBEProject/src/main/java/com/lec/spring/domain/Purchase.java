package com.lec.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Purchase {
    private Integer id;
    private Integer user_id;
    private Integer item_id;
    private LocalDateTime regdate;
    private String address; // 주소
    private String detail_address;  // 상세주소
    private String phone;
    private String request; // 문의사항


}
