package com.trueaccord.takehome;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.trueaccord.takehome.config.TestApplicationConfig;
import com.trueaccord.takehome.dao.model.Debt;
import com.trueaccord.takehome.dao.model.Payment;
import com.trueaccord.takehome.dao.model.PaymentPlan;
import com.trueaccord.takehome.dto.DebtDTO;
import com.trueaccord.takehome.enums.InstallmentFrequency;
import com.trueaccord.takehome.service.DebtService;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = TestApplicationConfig.class)
public class DebtServiceTest {

    private Gson gson = new Gson();

    @Autowired
    DebtService debtService;

    @Test
    public void testDebtAmountRemaining() {
        Debt debt = getDebtList().get(0);
        Map<Integer, PaymentPlan> paymentPlan = getPaymentPlans();
        Map<Integer, List<Payment>> payments = getPayments();

        PaymentPlan targetPlan = paymentPlan.get(debt.getId());
        assertNotNull(targetPlan);

        assertEquals(debtService.calculateRemainingAmount(debt, targetPlan, payments), 20.96);
    }

    @Test
    public void testDisplayingAllDebts() {
        List<Debt> debts = getDebtList();
        Map<Integer, PaymentPlan> paymentPlan = getPaymentPlans();
        Map<Integer, List<Payment>> payments = getPayments();
        List<DebtDTO> summary = debtService.summarizeDebt(debts, paymentPlan, payments);

        assertEquals(summary.size(), debts.size());
    }

    @Test
    public void testNextPaymentDueDate() {
        PaymentPlan paymentPlan = new PaymentPlan(1, 100, InstallmentFrequency.WEEKLY.name(),
                                                25, "2020-08-01", 1);
        LocalDate startDate = LocalDate.parse(paymentPlan.getStartDate());
        LocalDate today = LocalDate.now();
        int diff = startDate.getDayOfWeek() - today.getDayOfWeek();

        LocalDate nextDueDate = new LocalDate(debtService.getNextPaymentDueDate(paymentPlan, 150.0));

        assertEquals(nextDueDate.getDayOfWeek(), startDate.getDayOfWeek());
        assertEquals(Days.daysBetween(today, nextDueDate).getDays(), diff);

        // assert next due date is null
        assertEquals(debtService.getNextPaymentDueDate(paymentPlan, 0.0), null);
    }

    public List<Debt> getDebtList() {
        BufferedReader br = getFileReader("src/test/resources/debts.json");
        List<Debt> debts = gson.fromJson(br, new TypeToken<List<Debt>>(){}.getType());
        return debts;
    }

    public Map<Integer, PaymentPlan> getPaymentPlans() {
        BufferedReader br = getFileReader("src/test/resources/payment_plans.json");
        List<PaymentPlan> paymentPlans = gson.fromJson(br, new TypeToken<List<PaymentPlan>>(){}.getType());
        Map<Integer, PaymentPlan> paymentPlanMap = new HashMap<>();

        for(PaymentPlan plan : paymentPlans) {
            paymentPlanMap.put(plan.getDebtId(), plan);
        }

        return paymentPlanMap;
    }

    public Map<Integer, List<Payment>> getPayments() {
        BufferedReader br = getFileReader("src/test/resources/payments.json");
        List<Payment> payments = gson.fromJson(br, new TypeToken<List<Payment>>(){}.getType());
        Map<Integer, List<Payment>> map = new HashMap<>();

        for(Payment payment : payments) {
            if(map.containsKey(payment.getPaymentPlanId())) {
                map.get(payment.getPaymentPlanId()).add(payment);
            } else {
                List<Payment> newPaymentList = new ArrayList<>();
                newPaymentList.add(payment);
                map.put(payment.getPaymentPlanId(), newPaymentList);
            }
        }
        return map;
    }

    private BufferedReader getFileReader(String filepath) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filepath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return br;
    }
}
