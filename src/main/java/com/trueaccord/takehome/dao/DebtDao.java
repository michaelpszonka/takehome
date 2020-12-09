package com.trueaccord.takehome.dao;

import com.trueaccord.takehome.dao.model.Debt;

import java.util.List;

public interface DebtDao {
    List<Debt> getAllDebts();
}
