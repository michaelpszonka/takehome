package com.trueaccord.takehome;

import com.trueaccord.takehome.dao.impl.DebtDaoImpl;
import com.trueaccord.takehome.dao.impl.PaymentDaoImpl;
import com.trueaccord.takehome.dao.impl.PaymentPlanDaoImpl;
import com.trueaccord.takehome.service.DebtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
public class TakehomeApplication implements CommandLineRunner {

	@Autowired
	DebtService debtService;

	@Autowired
	private ConfigurableApplicationContext context;

	Logger logger = LoggerFactory.getLogger(DebtService.class);

	public static void main(String[] args) {
		SpringApplication.run(TakehomeApplication.class, args);
	}

	@Override
	public void run(String... args) {
		logger.info("-----EXECUTING TAKE HOME APPLICATION-----");

		debtService.displayDebts();
		System.exit(SpringApplication.exit(context));
	}

}
