package com.trueaccord.takehome.dao.impl;

import com.trueaccord.takehome.dao.PaymentPlanDao;
import com.trueaccord.takehome.dao.model.Debt;
import com.trueaccord.takehome.dao.model.PaymentPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class PaymentPlanDaoImpl implements PaymentPlanDao {

    @Value("${com.trueaccord.takehome.paymentplan.api.url}")
    private String paymentPlanApiUrl;

    @Autowired
    private RestTemplate restTemplate;

    public List<PaymentPlan> getAllPaymentPlans() {
        ResponseEntity<List<PaymentPlan>> paymentPlanResponse = restTemplate.exchange(paymentPlanApiUrl, HttpMethod.GET, null,
                                                                            new ParameterizedTypeReference<List<PaymentPlan>>() {});

        return paymentPlanResponse.getBody();

    }

}
