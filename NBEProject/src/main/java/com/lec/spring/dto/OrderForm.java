package com.lec.spring.dto;

import lombok.Data;

@Data
public class OrderForm {
   private String pg;
   private String pay_method;
   private String merchantId;
   private String name;
   private Integer amount;
   private String email;
   private String buyerName;
   private String tel;
   private String addr;

   public OrderForm(OrderUser orderUser, String merchantId, String name, Integer amount){
        this.pg = "html5_inicis.INIpayTest";
        this.pay_method = "card";
        this.merchantId = merchantId;
        this.name = name;
        this.amount = amount;
        this.email = orderUser.getEmail();
        this.buyerName = orderUser.getName();
        this.tel = orderUser.getPhone();
        this.addr = orderUser.getStreetAddr() + "\n" + orderUser.getDetailAddr();
   }
}
