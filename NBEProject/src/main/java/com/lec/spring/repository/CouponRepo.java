package com.lec.spring.repository;

import com.lec.spring.domain.shop.Coupon;

public interface CouponRepo {
    // 쿠폰 생성
    int insert(Coupon coupon);
    // 쿠폰 삭제
    int delete(int id);
}
