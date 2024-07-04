package com.lec.spring.service;

import com.lec.spring.domain.ReviewGoods;
import com.lec.spring.domain.shop.Review;

public interface ReviewService {

    // 특정 정보 아이템 정보들 갖고오기
    ReviewGoods getPurchaseInfo(Integer id);

    // 문의사항 입력
    int writeReview(Review review);

}

