package com.lec.spring.service;

import com.lec.spring.domain.shop.Address;
import com.lec.spring.domain.shop.Cart;
import com.lec.spring.domain.shop.Purchase;
import com.lec.spring.repository.AddressRepo;
import com.lec.spring.repository.CartRepo;
import com.lec.spring.repository.PurchaseRepo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class CartServiceImpl implements CartService {

    private CartRepo cartRepo;
    private PurchaseRepo purchaseRepo;
    private AddressRepo addressRepo;


    @Autowired
        public CartServiceImpl(SqlSession sqlSession){
            cartRepo = sqlSession.getMapper(CartRepo.class);
            purchaseRepo = sqlSession.getMapper(PurchaseRepo.class);
            addressRepo = sqlSession.getMapper(AddressRepo.class);

        System.out.println("CartService() 생성");
    }


    // 장바구니 등록
    @Override
    public int addCart(Cart cart) {
        int result = cartRepo.insert(cart);
        if (result != 1) {
            throw new RuntimeException("장바구니 등록 실패");
        }
        return result;

    }


    // 장바구니 삭제
    @Override
    public int removeCart(int user_id, int item_id) {
        int result = cartRepo.delete(user_id, item_id);
        if (result != 1) {
            throw new RuntimeException("장바구니 삭제 실패");
        }
        return result;
    }

    // 사용자가 장바구니에 있는 상품을 구매하면 해당 장바구니 상품 목록 삭제 기능
    @Override
    @Transactional
    public int purchaseItem(int user_id, int item_id, Address address, String street_addr, String detail_address, String phone, String request) {
        // 주문 생성


        Purchase purchase = Purchase.builder()
                .user_id(user_id)
                .item_id(item_id)
                .regdate(LocalDateTime.now())
                .street_addr(address.getStreet_addr())
                .detail_address(address.getDetail_addr())
                .phone(phone)
                .request(request)
                .build();

        int result = purchaseRepo.insert(purchase);

         result = cartRepo.delete(user_id, item_id);
        return result;
    }


}
