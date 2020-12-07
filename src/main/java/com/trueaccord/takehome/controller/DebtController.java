package com.trueaccord.takehome.controller;

import com.trueaccord.takehome.dao.model.Debt;
import com.trueaccord.takehome.service.DebtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value = "/debts")
@RestController
public class DebtController {

    @Autowired
    DebtService debtService;

    @GetMapping(path = "", produces = "application/json")
    public List<Debt> getAllDebts() {
        return debtService.getAllDebts();
    }
}
