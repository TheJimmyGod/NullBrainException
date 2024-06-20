package com.lec.spring.domain.shop;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    private Integer id;
    private String content;
    private LocalDateTime regDate;

    @ToString.Exclude
    @Builder.Default // builder 제공 안 함
    private List<PostImage> imageList = new ArrayList<>();

    private User user;

}