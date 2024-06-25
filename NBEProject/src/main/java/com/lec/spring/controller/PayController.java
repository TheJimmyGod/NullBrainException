package com.lec.spring.controller;

import com.lec.spring.domain.CartPurchaseItem;
import com.lec.spring.domain.User;
import com.lec.spring.domain.PurchaseItem;
import com.lec.spring.service.CartService;
import com.lec.spring.service.PayService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class PayController {

    @Autowired
    private PayService payService;

    @Autowired
    private CartService cartService;




    @GetMapping("/cart")
    public String cart(@RequestParam("userId") Integer userId, Model model) {
        List<CartPurchaseItem> CartpurchaseItemList = cartService.listPurchaseItems(userId);
        int totalPrice = 0;

        for (CartPurchaseItem item : CartpurchaseItemList) {
            totalPrice += item.getCount() * item.getPrice();
        }

        model.addAttribute("CartpurchaseItemList", CartpurchaseItemList);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("userId", userId);

        return "cart";
    }

    @PostMapping("/cart")
    public String cartOk(@Valid CartPurchaseItem cartPurchaseItem
            , @RequestParam("delitem") Integer[] delitem
            , @RequestParam("selectitem") Integer[] selectItem
            , @RequestParam("userId") Integer userId
            , Model model
    ) {

        if (delitem == null) {
            System.out.println("No items to delete");
            model.addAttribute("result", 0);
        } else {
            System.out.println("Deleting items: " + Arrays.toString(delitem));
            int result = cartService.deleteitem(delitem);
            model.addAttribute("result", result);
        }

        if (selectItem == null) {
            System.out.println("No items to select");
            model.addAttribute("result1", 0);
        } else {
            System.out.println("Selecting items: " + Arrays.toString(selectItem));
            int result1 = cartService.selectitem(selectItem);
            model.addAttribute("result1", result1);

        }

        model.addAttribute("userId", userId);

        return "cartOk";
    }

//    @PostMapping("/check")
//    public String check(@Valid CartPurchaseItem cartPurchaseItem
//            , @RequestParam("selectitem") Integer[] selectItem
//            , @RequestParam("userId") Integer userId
//            , Model model
//    ) {
//
//        if (selectItem == null) {
//            System.out.println("No items to select");
//            model.addAttribute("result1", 0);
//        } else {
//            System.out.println("Selecting items: " + Arrays.toString(selectItem));
//            int result1 = cartService.selectitem(selectItem);
//            model.addAttribute("result1", result1);
//
//        }
//
//        model.addAttribute("userId", userId);
//
//        return "check";
//    }

    @GetMapping("/pay")
    public String payment(@RequestParam("userId") Integer userId, Model model){
        User user = payService.getUserInfo(userId);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("phone", user.getPhone());

        return "pay";
    }


}
