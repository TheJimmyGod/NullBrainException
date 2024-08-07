package com.lec.spring.controller;


import com.lec.spring.domain.User;
import com.lec.spring.domain.shop.Address;
import com.lec.spring.domain.shop.Cart;
import com.lec.spring.dto.OrderForm;
import com.lec.spring.service.CartService;
import com.lec.spring.service.PurchaseService;
import com.lec.spring.service.UserService;
import com.lec.spring.util.U;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
public class OrderController {
    private final CartService cartService;
    private final PurchaseService purchaseService;

    private final UserService userService;




    List<Cart> selectedItems = new ArrayList<>();

    public OrderController(CartService cartService,
                           PurchaseService purchaseService, UserService userService
    )
    {
        this.cartService = cartService;
        this.purchaseService = purchaseService;
        this.userService = userService;
    }




    @GetMapping("/cart")
    public String cart(@RequestParam(required = false) Integer cartId
            , Model model) {
        List<Cart> cartList = cartService.listUserItems(U.getLoggedUser().getId());
        int totalPrice = 0;

        for (Cart item : cartList) {
            totalPrice += item.getAmount() * Integer.parseInt(item.getGoods().getPrice());
        }

        model.addAttribute("cartItem", cartList);
        model.addAttribute("purchase", cartId);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("userId", U.getLoggedUser().getId());


        return "cho/order/cart";
    }

    @PostMapping("/cart/refresh")
    public String refreshCart(@Valid Cart PurchaseItem
            , @RequestParam(name="selectedItem", required = false) Integer[] selectItem
            , @RequestParam("userId") Integer userId
            , Model model
    ) {
        selectedItems.clear();
        if(selectItem != null){
            if(cartService.deleteItems(selectItem) > 0){
                System.out.println("삭제 성공");
            }
            else System.out.println("삭제 실패");

        }

        return "redirect:/cart";
    }

    @GetMapping("/pay")
    public String purchasePage(
            @RequestParam(name="selectedItem", required = false) Integer[] selectItem,
            Model model){
        if(selectItem.length == 0){
            return "redirect:/cart";
        }
        User user = U.getLoggedUser();
        List<Cart> itemList = cartService.selectItems(selectItem);
        OrderForm orderForm = purchaseService.createPurchase(itemList, user.getId());
        Address address = userService.getDefaultAddr(user.getId());

        int totalPrice = 0;
        int totalCnt = 0;

        for (Cart item : itemList) {
            totalPrice += item.getAmount() * Integer.parseInt(item.getGoods().getPrice());
            totalCnt += item.getAmount();
        }


        model.addAttribute("userName", user.getName());
        model.addAttribute("phone", user.getPhone());
        model.addAttribute("address", address.getStreet_addr());
        model.addAttribute("detailAddress", address.getDetail_addr());
        model.addAttribute("totalCnt", totalCnt);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("order", orderForm);
        model.addAttribute("itemList", itemList);
        return "cho/order/pay";
    }








}



