package com.lec.spring.controller;

import com.lec.spring.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    public CartController(){
        System.out.println("CartController() 생성");
    }


}
