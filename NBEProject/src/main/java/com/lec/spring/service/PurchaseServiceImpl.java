package com.lec.spring.service;


import com.lec.spring.domain.shop.Cart;
import com.lec.spring.domain.shop.Pay;
import com.lec.spring.domain.shop.Purchase;
import com.lec.spring.dto.OrderForm;
import com.lec.spring.dto.OrderUser;
import com.lec.spring.dto.PayStatus;
import com.lec.spring.repository.PayRepo;
import com.lec.spring.repository.PurchaseRepo;
import com.lec.spring.repository.UserRepo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replaceAll("-","");
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        String dateTime = dateFormat.format(new Date());
        String merchantId = uuid + "-" + dateTime;
        int totalPrice = 0;
        OrderUser orderUser = userRepo.selectById(userId).oderUser();
        StringBuilder name = new StringBuilder();

        Pay pay = Pay.builder()
                .price(totalPrice)
                .status(PayStatus.READY)
                .build();
        payRepo.insert(pay);


        for(Cart item : itemList){
            Purchase purchase = Purchase.builder()
                    .merchantId(merchantId)
                    .payId(pay.getId())
                    .amount(item.getAmount())
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
}
