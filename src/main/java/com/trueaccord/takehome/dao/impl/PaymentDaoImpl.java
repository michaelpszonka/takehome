package com.trueaccord.takehome.dao.impl;

import com.trueaccord.takehome.dao.PaymentDao;
import com.trueaccord.takehome.dao.model.Debt;
import com.trueaccord.takehome.dao.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class PaymentDaoImpl implements PaymentDao {

    @Value("${com.trueaccord.takehome.payment.api.url}")
    private String paymentApiUrl;

    @Autowired
    private RestTemplate restTemplate;

    public List<Payment> getAllPayments() {
        ResponseEntity<List<Payment>> paymentResponse = restTemplate.exchange(paymentApiUrl, HttpMethod.GET, null,
                                                                        new ParameterizedTypeReference<List<Payment>>() {});
        return paymentResponse.getBody();
    }

}
