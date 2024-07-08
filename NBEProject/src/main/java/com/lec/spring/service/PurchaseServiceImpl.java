package com.lec.spring.service;


import com.lec.spring.domain.User;
import com.lec.spring.domain.shop.Address;
import com.lec.spring.domain.shop.Cart;
import com.lec.spring.domain.shop.Pay;
import com.lec.spring.domain.shop.Purchase;
import com.lec.spring.dto.OrderForm;
import com.lec.spring.dto.OrderUser;
import com.lec.spring.dto.PayStatus;
import com.lec.spring.repository.PayRepo;
import com.lec.spring.repository.PurchaseRepo;
import com.lec.spring.repository.UserRepo;
import com.lec.spring.util.U;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class PurchaseServiceImpl implements PurchaseService {
    private final PurchaseRepo purchaseRepo;
    private final PayRepo payRepo;
    private final UserRepo userRepo;

    @Autowired
    public PurchaseServiceImpl(SqlSession sqlSession){
        purchaseRepo = sqlSession.getMapper(PurchaseRepo.class);
        payRepo = sqlSession.getMapper(PayRepo.class);
        userRepo = sqlSession.getMapper(UserRepo.class);
    }


    @Override
    public OrderForm createPurchase(List<Cart> carts, Integer userId) {
        List<Cart> itemList = carts;
        if(itemList == null){
            return null;
        }
        Address addr = userRepo.getDefaultAddr(userId);
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replaceAll("-","");
        String merchantId = uuid;
        int totalPrice = 0;
        OrderUser orderUser = userRepo.selectById(userId).oderUser(addr.getStreet_addr(), addr.getDetail_addr());
        StringBuilder name = new StringBuilder();

        Pay pay = Pay.builder()
                .price(totalPrice)
                .status(PayStatus.READY)
                .build();
        payRepo.insert(pay);


        for(Cart item : itemList){
            Purchase purchase = Purchase.builder()
                    .merchantId(merchantId)
                    .pay(pay)
                    .amount((item.getAmount() * Integer.parseInt(item.getGoods().getPrice())))
                    .opt(item.getOpt())
                    .goods(item.getGoods())
                    .user(orderUser)
                    .build();


            purchaseRepo.insert(purchase);
            totalPrice += item.getAmount() * Integer.parseInt(item.getGoods().getPrice());
            name.append(item.getGoods().getName());

            orderUser = item.getUser();
        }


        if(payRepo.insert(pay) < 0) System.out.println("결제 저장실패");
        OrderForm orderForm = new OrderForm(orderUser, merchantId, name.toString(), totalPrice);
        return orderForm;
    }

    @Override
    public int delete() {
        return purchaseRepo.delete();
    }

    @Override
    public int updateStatus(Purchase p) {
        return purchaseRepo.updateStatus(p);
    }

    @Override
    public Purchase findById(Integer id) {
        return purchaseRepo.findById(id);
    }

    @Override
    public List<Purchase> findPurchase(String mId) {
        return purchaseRepo.findByRequest(mId);
    }



    @Override
    public List<Purchase> myPurchase() {
        return purchaseRepo.findByUser(U.getLoggedUser().getId());
    }

    // 전체 주문 내역 조회
    @Override
    public List<Purchase> orderList() {
        List<Purchase> purchases = purchaseRepo.listOrder();
        for (Purchase purchase : purchases) {
            if (purchase.getUser() != null) {
                User users = userRepo.selectById(purchase.getUsers().getId());
                purchase.setUsers(users);
            }
        }
        return purchases;
    }

    @Override
    public Long orderCnt() {
        return purchaseRepo.cntOrder();
    }

    @Override
    public List<Purchase> orderUsername(String name) {
        List<Purchase> purchases = purchaseRepo.username(name);
        for (Purchase purchase : purchases) {
            if (purchase.getUsers() != null) {
                User users = userRepo.selectById(purchase.getUsers().getId());
                purchase.setUsers(users);
            }
        }
        return purchases;
    }
    // 사용자가 결제한 물건 가져와준다
    @Override
    public List<Purchase> getUserPayed(Integer userId) {

        return purchaseRepo.findByUser(userId);
    }

    @Override
    public Long cntPurchaseItem() {
        return purchaseRepo.cntPurchaseItem();
    }

    @Override
    public List<Purchase> pagination(int offset, int limit) {
        List<Purchase> purchases = purchaseRepo.pagination(offset, limit);
        for (Purchase purchase : purchases) {
            if (purchase.getUsers() != null) {
                User users = userRepo.selectById(purchase.getUsers().getId());
                purchase.setUsers(users);
            }
        }
        return purchases;
    }

    @Override
    public List<Purchase> userPagunation(String name, int offset, int limit) {
        return purchaseRepo.userPagination(name, offset, limit);
    }

    @Override
    public void updatePayStatus(Integer purchaseId, com.lec.spring.dto.PayStatus status) {
        purchaseRepo.updatePayStatus(purchaseId, status);
    }

    @Override
    public Long cntCompleted() {
        return purchaseRepo.cntStatusOK();
    }

    @Override
    public Long cntPending() {
        return purchaseRepo.cntStatusREADY();
    }

    @Override
    public Long cntFailed() {
        return purchaseRepo.cntStatusCANCEL();
    }
}
