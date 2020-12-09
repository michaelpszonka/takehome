package com.trueaccord.takehome.service;

import com.trueaccord.takehome.dao.impl.DebtDaoImpl;
import com.trueaccord.takehome.dao.impl.PaymentDaoImpl;
import com.trueaccord.takehome.dao.impl.PaymentPlanDaoImpl;
import com.trueaccord.takehome.dao.model.Debt;
import com.trueaccord.takehome.dao.model.Payment;
import com.trueaccord.takehome.dao.model.PaymentPlan;
import com.trueaccord.takehome.dto.DebtDTO;
import com.trueaccord.takehome.enums.InstallmentFrequency;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DebtService {

    Logger logger = LoggerFactory.getLogger(DebtService.class);

    @Autowired
    DebtDaoImpl debtDao;

    @Autowired
    PaymentPlanDaoImpl paymentPlanDao;

    @Autowired
    PaymentDaoImpl paymentDao;

    public void displayDebts() {
        List<DebtDTO> debts = summarizeDebts();
        debts.stream().forEach(debt-> System.out.println(debt.toString()));
    }

    private List<DebtDTO> summarizeDebts() {
        List<DebtDTO> summary = null;
        try {
            List<Debt> debts = getDebts();
            Map<Integer, PaymentPlan> debtToPaymentPlan = getDebtIdToPaymentPlan();
            Map<Integer, List<Payment>> paymentPlanToPayment = getPaymentPlanIdToPayments();

            summary = summarizeDebt(debts, debtToPaymentPlan, paymentPlanToPayment);
        }
        catch(HttpStatusCodeException e) {
            logger.error("There was an error retrieving debt and payment plan information from the api", e);
        }
        catch(RestClientException e) {
            logger.error("Error parsing api debt and payment plan response payload", e);
        }
        catch(Exception e) {
            logger.error("Unexpected exception while gathering debt and payment information" , e);
        }
        return summary;
    }

    /**
     * Transforms Debt Entity Objects to Debt DTO,
     * transforming Debt objects per business rules.
     * Calculates debt outstanding, next payment due date,
     * and flags debts that have been assigned to a payment plan
     * @param debts - all outstanding debts in debt table
     * @param debtToPaymentPlans - mapping of debt ids to associated payment plans
     * @param paymentPlanToPayments - mapping of payment plan ids to associated payments
     * @return - List of transformed Debts for output
     */
    public List<DebtDTO> summarizeDebt(List<Debt> debts, Map<Integer, PaymentPlan> debtToPaymentPlans,
                                        Map<Integer, List<Payment>> paymentPlanToPayments) {

        List<DebtDTO> summary = new ArrayList<>();

        for(Debt debt : debts) {
            DebtDTO debtDTO = new DebtDTO(debt);
            if(debtToPaymentPlans.containsKey(debt.getId())) {
                PaymentPlan plan = debtToPaymentPlans.get(debt.getId());
                debtDTO.setInPaymentPlan(true);
                debtDTO.setRemainingAmount(calculateRemainingAmount(debt, plan, paymentPlanToPayments));
                debtDTO.setNextPaymentDueDate(getNextPaymentDueDate(plan, debtDTO.getRemainingAmount()));
            } else {
                debtDTO.setInPaymentPlan(false);
                debtDTO.setRemainingAmount(debtDTO.getAmount());
                debtDTO.setNextPaymentDueDate(null);
            }
            summary.add(debtDTO);
        }
        return summary;
    }

    /**
     * Calculate next payment date given a payment plan and the amount outstanding.
     * TODO - Calculating next payment date from past payment
     *  (Apologies I was not sure how to derive next payment date by looking at past payment dates
     *  (w/out relating back to payment plan id and start date) as this assumes payment dates are made precisely on
     *  the a weekly / biweekly basis
     * @param plan
     * @return
     */
    public Date getNextPaymentDueDate(PaymentPlan plan, double remainingAmount) {
        if(plan == null || remainingAmount == 0.0) {
            return null;
        }

        LocalDate startDate = LocalDate.parse(plan.getStartDate());
        LocalDate today = LocalDate.now();
        int timeDiff = startDate.getDayOfWeek() - today.getDayOfWeek();

        try {
            if(plan.getInstallmentFrequency().equals(InstallmentFrequency.WEEKLY.name())) {
                if(timeDiff <= 0) {
                    timeDiff += 7;
                }
            } else if(plan.getInstallmentFrequency().equals(InstallmentFrequency.BI_WEEKLY.name())) {
                if(timeDiff <= 0) {
                    timeDiff += 14;
                }
            } else {
                throw new IllegalArgumentException("Pay installment frequency not defined");
            }
        }
        catch(IllegalArgumentException e) {
            logger.error("Error calculating payment date for debt id " + plan.getDebtId());
            e.printStackTrace();
        }
        return today.plusDays(timeDiff).toDate();
    }

    public double calculateRemainingAmount(Debt debt, PaymentPlan plan, Map<Integer, List<Payment>> planIdToPayments) {
        double remainingAmount = debt.getAmount();
        List<Payment> payments = planIdToPayments.getOrDefault(plan.getId(), new ArrayList<>());
        for(Payment payment : payments) {
            remainingAmount -= payment.getAmount();
        }

        return Math.round(remainingAmount * 100.0) / 100.0;
    }

    /**
     * Retrieve all Debts from the DB
     * @return
     */
    private List<Debt> getDebts() {
        return debtDao.getAllDebts().stream().collect(Collectors.toList());
    }

    /**
     * Retrieve Payment Plans and associate by debt id for constant lookup times
     * @return
     */
    private Map<Integer, PaymentPlan> getDebtIdToPaymentPlan() {
        return paymentPlanDao.getAllPaymentPlans().stream()
                                                    .collect(Collectors.toMap(PaymentPlan::getDebtId, Function.identity()));
    }

    /**
     * Retrieve Payments from api and associate by payment plan ids for constant lookup times
     * @return
     */

    private Map<Integer, List<Payment>> getPaymentPlanIdToPayments() {
        Map<Integer, List<Payment>> planToPaymentsMap = new HashMap<>();
        List<Payment> payments = paymentDao.getAllPayments().stream().collect(Collectors.toList());

        for(Payment payment : payments) {
            if(planToPaymentsMap.containsKey(payment.getPaymentPlanId())) {
                planToPaymentsMap.get(payment.getPaymentPlanId()).add(payment);
            } else {
                List<Payment> newPaymentList = new ArrayList<>();
                newPaymentList.add(payment);
                planToPaymentsMap.put(payment.getPaymentPlanId(), newPaymentList);
            }
        }
        return planToPaymentsMap;
    }
}
