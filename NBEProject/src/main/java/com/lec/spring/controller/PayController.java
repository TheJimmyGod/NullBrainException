package com.lec.spring.controller;

import com.lec.spring.domain.shop.Address;
import com.lec.spring.domain.CartPurchaseItem;
import com.lec.spring.domain.User;
import com.lec.spring.service.CartService;
import com.lec.spring.service.PayService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
public class PayController {

    @Autowired
    private PayService payService;

    @Autowired
    private CartService cartService;

    List<CartPurchaseItem> selectedItems = new ArrayList<>();


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
            , @RequestParam(name="delitem", required = false) Integer[] delitem
            , @RequestParam(name="selectitem", required = false) Integer[] selectItem
            , @RequestParam("userId") Integer userId
            , Model model
    ) {
        selectedItems.clear();
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
            selectedItems = cartService.selectitem(selectItem);

            System.out.println(selectedItems);
            model.addAttribute("result1", selectedItems);
            model.addAttribute("resultSize", selectedItems.size());
        }

        model.addAttribute("userId", userId);

        return "cartOk";
    }

    @GetMapping("/pay")
    public String payment(@RequestParam("userId") Integer user_Id, Model model){
        User user = payService.getUserInfo(user_Id);
        Address address = payService.defaultAddr(user_Id);

        int totalPrice = 0;

        for (CartPurchaseItem item : selectedItems) {
            totalPrice += item.getCount() * item.getPrice();
        }

        // 특정 유저의 기본 배송지 정보
        model.addAttribute("streetAddr", address.getStreet_addr());
        model.addAttribute("detailAddr",address.getDetail_addr());

        // 특정 유저의 정보
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("phone", user.getPhone());
        model.addAttribute("result1", selectedItems);
        model.addAttribute("resultSize", selectedItems.size());
        return "pay";
    }


}



