package com.lec.spring.config.oauth.provider;

import java.util.Map;

public class KaKaoUserInfo implements OAuth2UserInfo{

    private Map<String, Object> attributes;
    private Map<String, Object> kakaoAccount;
    private Map<String, Object> profile;

    public KaKaoUserInfo (Map<String, Object> attributes) {
        this.attributes = attributes;
        this.kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        this.profile = (Map<String, Object>) kakaoAccount.get("profile");
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getProviderId() {
        return String.valueOf(attributes.get("id"));
    }

    @Override
    public String getEmail() {
        return "none@example.com";
    }

    @Override
    public String getName() {
        return (String) profile.get("nickname");
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }
}
