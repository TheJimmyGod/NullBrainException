package com.lec.spring.config.oauth;

import com.lec.spring.config.PrincipalDetails;
import com.lec.spring.config.oauth.provider.*;
import com.lec.spring.domain.User;
import com.lec.spring.dto.UserDto;
import com.lec.spring.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserService userService; //회원가입용

    @Value("12345678")
    private String oauth2Password; //기본 pw

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
        System.out.println("OAuth2UserService.loadUser()호출");

        OAuth2User oAuth2User = super.loadUser(userRequest);

        System.out.println(userRequest.getClientRegistration());
        System.out.println(userRequest.getClientRegistration().getRegistrationId());
        System.out.println(userRequest.getAccessToken().getTokenValue());
        System.out.println(oAuth2User.getAttributes());

        String provider = userRequest.getClientRegistration().getRegistrationId();

        OAuth2UserInfo oAuth2UserInfo = switch(provider.toLowerCase()){
            case "google" -> new GoogleUserInfo(oAuth2User.getAttributes());
            case "facebook" -> new FacebookUserInfo(oAuth2User.getAttributes());
            case "naver" -> new NaverUserInfo(oAuth2User.getAttributes());
            case "kakao" -> new KaKaoUserInfo(oAuth2User.getAttributes());
            default -> null;
        };

        if(oAuth2UserInfo == null){
            System.out.println("oAuth2UserInfo가 null 입니다.");
            throw new OAuth2AuthenticationException("oAuth2UserInfo == null" + provider);

        }

        String providerId = oAuth2UserInfo.getProviderId();
        String username = provider + "_" + providerId;
        String password = oauth2Password;
        String email = oAuth2UserInfo.getEmail();
        String name = oAuth2UserInfo.getName();

        User user = userService.findByUsername(username);
        if(user == null){

            request.getSession().setAttribute("oauth2UserInfo", oAuth2UserInfo);
            User newUser = User.builder()
                    .username(username)
                    .name(name)
                    .email(email)
                    .password(password)
                    .provider(provider)
                    .providerId(providerId)
                    .phone("none")
                    .birth("none")
                    .build();

            int cnt = userService.register(newUser);
            if(cnt > 0){
                System.out.println("[OAuth2 인증. 회원가입 성공.]");
                user = userService.findByUsername(username);
            }else {
                System.out.println("[OAuth2 인증. 회원 가입 실패.]");
            }


        }else {
            System.out.println("OAuth2 인증. 이미 가입된 회원입니다.");

        }
        PrincipalDetails principalDetails = new PrincipalDetails(user, oAuth2User.getAttributes());
        principalDetails.setUserService(userService);
        return principalDetails;



    }

}
