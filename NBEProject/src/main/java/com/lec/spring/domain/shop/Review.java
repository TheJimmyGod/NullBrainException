package com.lec.spring.domain.shop;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lec.spring.domain.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Review {
    private Integer id;
    // 유저 참조키
    private User user;
    @JsonProperty("goods_id")
    private String goodsId;
    private LocalDateTime regdate;
    private String title;
    private String content;
    private Double rate;

    @ToString.Exclude
    @Builder.Default
    private List<ReviewImage> images = new ArrayList<>();
}
