package com.trueaccord.takehome.repository;

import com.trueaccord.takehome.dao.model.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Integer> {

}
