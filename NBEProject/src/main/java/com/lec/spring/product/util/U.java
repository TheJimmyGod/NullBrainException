package com.lec.spring.product.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class U {

    // 현재 로그인 한 사용자 User
//    public static User getLoggedUser(){
//        PrincipalDetails userDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User user = userDetails.getUser();
//        return user;
//    }

    // 현제 요청 구하기
    public static HttpServletRequest getRequest(){
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attrs.getRequest();
    }
    // 현제 세션 구하기
    public static HttpSession getSession(){return getRequest().getSession();}
}
