package com.lec.spring.config;

import com.lec.spring.domain.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CustomOAuth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    public CustomOAuth2LoginSuccessHandler(String defaultTargetUrl){
        setDefaultTargetUrl(defaultTargetUrl);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException{
        System.out.println("OAuth2 로그인 성공");

        PrincipalDetails userDetails = (PrincipalDetails)authentication.getPrincipal();

        List<String> roleNames = new ArrayList<>();
        authentication.getAuthorities().forEach(authority -> {
            roleNames.add(authority.getAuthority());
        });
        System.out.println("권한: " + roleNames);

        LocalDateTime loginTime = LocalDateTime.now();
        request.getSession().setAttribute("loginTime", loginTime);

        if(roleNames.contains("ROLE_ADMIN")){
            response.sendRedirect("/admin/main");
            return;
        }

        super.onAuthenticationSuccess(request, response, authentication);

        Integer id = userDetails.getUser().getId();
        System.out.println(id);
        System.out.println(userDetails.getUser());

//        String prov = userDetails.getUser().getProviderId();

//        System.out.println(prov);
//        if(prov.equals("naver") || prov.equals("facebook") || prov.equals("google") ) {
//
//            System.out.println("naver/facebook/google 로그인성공");
//
//
//
//        }





    }
}
