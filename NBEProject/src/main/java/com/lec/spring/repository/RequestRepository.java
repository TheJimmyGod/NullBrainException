package com.lec.spring.repository;

import com.lec.spring.domain.reviewGoods;
import com.lec.spring.domain.shop.Purchase;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RequestRepository {

    // user_id로 아이템들의 정보 갖고오기
    List<reviewGoods> findByUserId(@Param("userId") int userId);

}
