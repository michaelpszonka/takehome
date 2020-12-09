package com.trueaccord.takehome.dao;

import com.trueaccord.takehome.dao.model.PaymentPlan;

import java.util.List;

public interface PaymentPlanDao {

    List<PaymentPlan> getAllPaymentPlans();
}
