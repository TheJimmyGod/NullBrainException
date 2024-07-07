package com.lec.spring.domain.shop;


import com.lec.spring.domain.User;
import com.lec.spring.dto.OrderGoods;
import com.lec.spring.dto.OrderUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {
    private Integer id;
    private String merchantId;
    private LocalDateTime regdate;
    private Integer amount;
    private String opt;
    // 사용자가 결제취소요청, 리뷰작성여부확인가능
    private String status;
    private OrderGoods goods;
    private OrderUser user;

    // Pay 참조키
    private Pay pay;



    // 유저의 객체 정보
    private User users;


    // 상품의 정보
    private Goods good;


    // 총 가격 필드 추가
    private Integer totalPrice;

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }
}
