package com.lec.spring.controller;

import com.lec.spring.config.PrincipalDetails;
import com.lec.spring.config.oauth.provider.OAuth2UserInfo;
import com.lec.spring.domain.User;
import com.lec.spring.dto.UserDto;
import com.lec.spring.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OAuthController {

    @Autowired
    private UserService userService;

    @Value("eb290sasdjw0")
    private String oauth2Password;

//    @RequestMapping("/user/enterAddress")
//    private String enterAddress(){
//        return "/user/enterAddress";
//    }

    @PostMapping("/user/registerOAuth")
    public String registerOAuth(HttpServletRequest request,
                                @RequestParam("phone") String phone,
                                @RequestParam("birth") String birth,
                                @RequestParam("addressName") String addressName,
                                @RequestParam("street_addr") String streetAddr,
                                @RequestParam("detail_addr") String detailAddr){
        OAuth2UserInfo oAuth2UserInfo = (OAuth2UserInfo) request.getSession().getAttribute("oauth2UserInfo");

        if(oAuth2UserInfo == null){
            return "error";
        }

        String provider = oAuth2UserInfo.getProvider();
        String providerId = oAuth2UserInfo.getProviderId();
        String username = provider + "_" + providerId;
        String password = oauth2Password;
        String email = oAuth2UserInfo.getEmail();
        String name = oAuth2UserInfo.getName();

        UserDto newUserDto = UserDto.builder()
                .username(username)
                .name(name)
                .email(email)
                .password(password)
                .phone(phone)
                .birth(birth)
                .addressName(addressName)
                .street_addr(streetAddr)
                .detail_addr(detailAddr)
                .provider(provider)
                .providerId(providerId)
                .build();

        userService.register(newUserDto);

        request.getSession().removeAttribute("oauth2UserInfo");


        User newUser = userService.findByUsername(username);
        PrincipalDetails principalDetails = new PrincipalDetails(newUser, oAuth2UserInfo.getAttributes());

        //Authentication 객체를 생성
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());

        //사용자 정보 다시 로드 -> SecurityContext에 설정
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);


        return "redirect:/user/test";

    }

}
