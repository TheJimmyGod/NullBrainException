package com.lec.spring.repository;

import com.lec.spring.domain.shop.Review;

import java.util.List;

public interface ReviewRepo {
    // 리뷰생성
    int insert(Review review);
    // 리뷰삭제
    int delete(int id);
    // 리뷰수정
    int update(Review review);
    // 사용자 모든리뷰 조회
    List<Review> selectReviewByGoods(String goodNo, int fromRow, int pageRow);

    // 특정 리뷰 조회
    int countReview(String goodNo);
}
