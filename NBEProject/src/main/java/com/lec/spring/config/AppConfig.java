package com.lec.spring.config;

import com.siot.IamportRestClient.IamportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    String apiKey = "0001678527508551";
    String secretKey = "1ymb4aRRM6XB9KYg5TF2gTwLFDDSKajZInO4JIeiLckVVa2shVpA85PDxgYmOTIHImoHoRqxbMnqRN8M";

    @Bean
    public IamportClient iamportClient() {
        return new IamportClient(apiKey, secretKey);
    }
}
