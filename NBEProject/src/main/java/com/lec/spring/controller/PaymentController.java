package com.lec.spring.controller;


import com.lec.spring.dto.PaymentRequest;
import com.lec.spring.service.PayService;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

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

    @PostMapping("/payment/cancel")
    public void createPayment(@RequestBody PaymentRequest paymentRequest) throws IamportResponseException, IOException {
        IamportResponse<Payment> response = iamportClient.paymentByImpUid(paymentRequest.getImp_uid());
        CancelData cancelData = new CancelData(response.getResponse().getCustomerUid(), true);
        iamportClient.cancelPaymentByImpUid(cancelData);
    }


}





