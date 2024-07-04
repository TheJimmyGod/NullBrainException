package com.lec.spring.repository;

import com.lec.spring.domain.ReviewGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RequestRepository {

    // user_id로 아이템들의 정보 갖고오기
    List<ReviewGoods> findByUserId(@Param("userId") int userId);

}
