package com.lec.spring.domain.shop;


import com.lec.spring.domain.User;
import com.lec.spring.dto.OrderGoods;
import com.lec.spring.dto.OrderUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {
    private Integer id;
    private String merchantId;
    // Pay 참조키
    private Integer payId;
    private Integer amount;
    private String opt;
    private OrderGoods goods;
    private OrderUser user;


    // 페이 상태
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
