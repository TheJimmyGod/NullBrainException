package com.lec.spring.controller;

import com.lec.spring.domain.User;
import com.lec.spring.domain.ReviewGoods;

import com.lec.spring.domain.shop.Purchase;
import com.lec.spring.dto.PayStatus;
import com.lec.spring.service.PurchaseService;
import com.lec.spring.service.RequestService;
import com.lec.spring.service.UserService;
import com.lec.spring.util.U;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Controller
public class RequestController {

    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private UserService userService;

    @GetMapping("/request")
    public String request(Model model){
        Integer id = U.getLoggedUser().getId();
        User user = userService.findById(id);
        List<Purchase> PurchaseStatusList = purchaseService.getUserPayed(user.getId());
        model.addAttribute("userId", user.getId());
        model.addAttribute("PurchaseStatusList", PurchaseStatusList);
        return "request";
    }

    @GetMapping("/cancel")
    public ResponseEntity<String> cancelRequest(@RequestParam String mId){
        List<Purchase> purchaseList = purchaseService.findPurchase(mId);
        for(Purchase p : purchaseList){
            p.setStatus("CANCEL_OK");
            purchaseService.updateStatus(p);
        }
        return ResponseEntity.ok().build();
    }



}
