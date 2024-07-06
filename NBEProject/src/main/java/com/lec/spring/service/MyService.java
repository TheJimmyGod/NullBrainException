package com.lec.spring.service;

import com.lec.spring.domain.shop.Address;
import com.lec.spring.domain.shop.Profile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

public interface MyService {
    void showMyPage(Model model);
    void updateMyPage(Model model);
    @Transactional
    int updateProfile(Profile profile, Address[] delAddresses);
}
