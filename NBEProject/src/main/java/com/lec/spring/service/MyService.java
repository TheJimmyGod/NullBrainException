package com.lec.spring.service;

import com.lec.spring.domain.shop.Profile;
import org.springframework.ui.Model;

public interface MyService {
    void showMyPage(Model model);
    void updateMyPage(Model model, Integer user_id);
    int updateProfile(Integer user_id, Profile profile);
}
