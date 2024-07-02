package com.lec.spring.config.oauth.provider;

import java.util.Map;

public interface OAuth2UserInfo {
    String getProvider();

    String getProviderId();

    String getEmail();

    String getName();

    Map<String, Object> getAttributes();
}
