package com.lec.spring.repository;

import com.lec.spring.domain.Purchase;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RequestRepository {

    // 특정 유저의 아이디로 아이템들의 배송상태 찾기
    @Transactional
    List<Purchase> findByUserId(@Param("userId") Integer userId);

}
