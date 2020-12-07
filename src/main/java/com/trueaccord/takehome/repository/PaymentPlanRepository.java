package com.trueaccord.takehome.repository;

import com.trueaccord.takehome.dao.model.PaymentPlan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentPlanRepository extends CrudRepository<PaymentPlan, Integer> {
}
