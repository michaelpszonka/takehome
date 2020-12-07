package com.trueaccord.takehome.repository;

import com.trueaccord.takehome.dao.model.Debt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebtRepository extends CrudRepository<Debt, Integer> {

}
