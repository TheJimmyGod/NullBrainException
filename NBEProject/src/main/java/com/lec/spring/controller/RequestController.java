package com.lec.spring.controller;

import com.lec.spring.domain.reviewGoods;

import com.lec.spring.domain.shop.Purchase;
import com.lec.spring.service.PurchaseService;
import com.lec.spring.service.RequestService;
import com.lec.spring.util.U;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@Controller
public class RequestController {

//    @Autowired
//    private RequestService requestService;

    @Autowired
    private PurchaseService purchaseService;
    @GetMapping("/request")
    public String request(Model model){
        List<Purchase> PurchaseStatusList = purchaseService
                .getUserPayed(U.getLoggedUser().getUsername());

        model.addAttribute("userId", U.getLoggedUser().getId());
        model.addAttribute("PurchaseStatusList", PurchaseStatusList);

        return "request";
    }






}
