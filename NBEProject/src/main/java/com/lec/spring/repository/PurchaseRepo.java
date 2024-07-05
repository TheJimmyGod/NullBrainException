package com.lec.spring.repository;



import com.lec.spring.domain.shop.Pay;
import com.lec.spring.domain.shop.Purchase;

import java.util.List;

public interface PurchaseRepo {

    int insert(Purchase purchase);
    int delete();
    List<Purchase> findByRequest(String orderUid);

    List<Purchase> findByUser(Integer userId);

    int update(Purchase purchase);


    // 주문 내역 카운트
    Long cntOrder();

    // 전체 주문 내역 확인
    public List<Purchase> listOrder();

    // 사용자의 이름으로 확인
    public List<Purchase> username(String name);


    public List<Pay> selectAllPays();


    // 페이지 네이션 기능
    List<Purchase> pagination(int offset, int limit);

    Long cntPurchaseItem();


    Long cntStatusOK();

    Long cntStatusREADY();

    Long cntStatusCANCEL();

    void updatePayStatus(Integer purchaseId, com.lec.spring.dto.PayStatus status);

}
