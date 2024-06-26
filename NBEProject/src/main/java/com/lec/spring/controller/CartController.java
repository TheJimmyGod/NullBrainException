package com.lec.spring.controller;


import com.lec.spring.domain.shop.Cart;
import com.lec.spring.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController

public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    // 장바구니에 이미 있을경우 처리필요
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/cart/plus")
    public ResponseEntity<String> addToCart(@RequestBody Cart cart){
        try{

            if(cartService.getByUserIdGoodsId(cart.getUser().getUserId()
                    , cart.getGoods().getGoodsNo()) != null){
                System.out.println("이미 존재하는 제품입니다.");
                return ResponseEntity.ok("exist");
            }
            Cart item = Cart.builder()
                    .user(cart.getUser())
                    .opt(cart.getOpt())
                    .amount(cart.getAmount())
                    .goods(cart.getGoods())
                    .build();

            cartService.insert(item);
            return ResponseEntity.ok("goCart");

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");
        }
    }
}
