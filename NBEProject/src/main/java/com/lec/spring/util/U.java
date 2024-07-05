package com.lec.spring.util;

import com.lec.spring.config.PrincipalDetails;
import com.lec.spring.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class U {
    public static User getLoggedUser(){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof PrincipalDetails) {
                PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();
                return userDetails.getUser();
            }
        } catch (ClassCastException e) {
            // 인증되지 않은 사용자가 접근하는 경우 예외 처리
            System.out.println("ClassCastException: " + e.getMessage());
        } catch (Exception e) {
            // 기타 예외 처리
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attrs.getRequest();
    }
    public static HttpSession getSession() {
        return getRequest().getSession();
    }
}
