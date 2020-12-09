package com.trueaccord.takehome.config;

import com.trueaccord.takehome.dao.impl.DebtDaoImpl;
import com.trueaccord.takehome.dao.impl.PaymentDaoImpl;
import com.trueaccord.takehome.dao.impl.PaymentPlanDaoImpl;
import com.trueaccord.takehome.service.DebtService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * Test configuration to prevent command line app
 * from executing during unit testing
 */
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = CommandLineRunner.class))
@EnableAutoConfiguration
public class TestApplicationConfig {

    @Bean
    public DebtService getDebtService() {
        return new DebtService();
    }

    @Bean
    public DebtDaoImpl getDebtDao() {
        return new DebtDaoImpl();
    }

    @Bean
    public PaymentDaoImpl getPaymentDao() {
        return new PaymentDaoImpl();
    }

    @Bean
    public PaymentPlanDaoImpl getPaymentPlanDao() {
        return new PaymentPlanDaoImpl();
    }

}
