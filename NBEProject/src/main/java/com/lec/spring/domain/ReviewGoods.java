package com.lec.spring.domain;

import com.lec.spring.domain.shop.Pay;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewGoods {

    private Integer id;
    private String m_id;
    private Integer user_id;
    private String amount;
    private String opt;
    private String status;
    private Integer price;
    private String image;
    private String goodsName;
    private String goodsId;
    private LocalDateTime regdate;

}
