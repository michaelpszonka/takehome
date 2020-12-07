package com.trueaccord.takehome.service;

import com.trueaccord.takehome.dao.model.Debt;
import com.trueaccord.takehome.repository.DebtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DebtService {

    @Autowired
    DebtRepository debtRepository;

    public List<Debt> getAllDebts() {
        List<Debt> debts = new ArrayList<>();
        debtRepository.findAll().forEach(debt -> debts.add(debt));
        return debts;

    }
}
