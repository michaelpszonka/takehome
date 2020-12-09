package com.trueaccord.takehome.dao;

import com.trueaccord.takehome.dao.model.Payment;

import java.util.List;

public interface PaymentDao {
    List<Payment> getAllPayments();
}
