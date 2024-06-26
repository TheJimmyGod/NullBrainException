package com.lec.spring.repository;

import com.lec.spring.domain.PurchaseItem;

public interface PurchaseItemRepo {
    // 옵션과 결합된 아이템
    int insert(PurchaseItem purchaseItem);

}
