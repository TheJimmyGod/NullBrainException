package com.lec.spring.repository;

import com.lec.spring.domain.reviewGoods;
import com.lec.spring.domain.shop.Review;
import org.apache.ibatis.annotations.Param;

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

    // 여기서부터
    // 리뷰작성 버튼을 누르면 해당 아이템의 정보 갖고오기
    reviewGoods findById(@Param("id") Integer id);

    // 리뷰 글 작성 (INSERT)
    int save(Review review);

}
