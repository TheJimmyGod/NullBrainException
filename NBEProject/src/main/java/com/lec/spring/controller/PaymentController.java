package com.lec.spring.controller;


import com.lec.spring.dto.CancelResponse;
import com.lec.spring.dto.PaymentRequest;
import com.lec.spring.dto.Token;
import com.lec.spring.service.PayService;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

@Controller
public class PaymentController {

    private final PayService payService;
    private final IamportClient iamportClient;

    public PaymentController(PayService payService, IamportClient iamportClient) {
        this.payService = payService;
        this.iamportClient = iamportClient;
    }
    @ResponseBody
    @RequestMapping("/payment")
    public IamportResponse<Payment> paymentByImpUid(@RequestBody PaymentRequest paymentRequest)
            throws IamportResponseException, IOException {
        payService.creatPay(paymentRequest);
        return iamportClient.paymentByImpUid(paymentRequest.getImp_uid());
    }

    @PostMapping("/request/cancel")
    public void cancelRequest(@RequestParam String imp_uid) throws IamportResponseException, IOException {
        URI uri = UriComponentsBuilder.fromUriString("https://api.iamport.kr/payments/cancel")
                .build()
                .toUri();
        String token = getToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBearerAuth(token);
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("imp_uid", imp_uid);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);
        ResponseEntity<CancelResponse> response = new RestTemplate().exchange(
                uri,
                HttpMethod.POST,
                entity,
                CancelResponse.class
                );
        System.out.println(response);
    }


public String getToken()
        throws IamportResponseException, IOException {
    URI uri = UriComponentsBuilder.fromUriString("https://api.iamport.kr/users/getToken")
            .build()
            .toUri();

    HttpHeaders headers = new HttpHeaders();


    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("imp_key", "0001678527508551");
    params.add("imp_secret", "1ymb4aRRM6XB9KYg5TF2gTwLFDDSKajZInO4JIeiLckVVa2shVpA85PDxgYmOTIHImoHoRqxbMnqRN8M");

    HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, headers);
    ResponseEntity<Token> result = new RestTemplate().exchange(
        uri,
        HttpMethod.POST,
        httpEntity,
        Token.class
    );
    String body = result.getBody().getResponse().getAccess_token();
    return body;
}


}





