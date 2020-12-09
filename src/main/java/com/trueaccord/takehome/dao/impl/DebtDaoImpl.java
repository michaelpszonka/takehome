package com.trueaccord.takehome.dao.impl;

import com.trueaccord.takehome.dao.DebtDao;
import com.trueaccord.takehome.dao.model.Debt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class DebtDaoImpl implements DebtDao {

    @Value("${com.trueaccord.takehome.debt.api.url}")
    private String debtApiUrl;

    @Autowired
    private RestTemplate restTemplate;

    public List<Debt> getAllDebts() {
        ResponseEntity<List<Debt>> debtResponse = restTemplate.exchange(debtApiUrl, HttpMethod.GET, null,
                                                                        new ParameterizedTypeReference<List<Debt>>() {});
        return debtResponse.getBody();
    }
}
