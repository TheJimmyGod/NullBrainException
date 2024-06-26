package com.lec.spring.repository;

import com.lec.spring.domain.CartPurchaseItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartRepository {
    @Transactional
    List<CartPurchaseItem> findPurchaseItem(@Param("userId") Integer userId);

    // 다중 항목 삭제 메서드 추가
    @Transactional
    int deletePurchaseItems(@Param("list") List<Integer> ids);

    @Transactional
    List<CartPurchaseItem> selectPurchaseItems(@Param("list") List<Integer> ids);

    CartPurchaseItem findById(@Param("id") Integer id);


}
