package com.trueaccord.takehome.dto;

import com.trueaccord.takehome.dao.model.PaymentPlan;
import com.trueaccord.takehome.enums.InstallmentFrequency;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.text.ParseException;
import java.util.Date;

@Data
@Getter @Setter
public class PaymentPlanDTO {
    private int id;
    private double amountToPay;
    private InstallmentFrequency installmentFrequency;
    private double installmentAmount;
    private String startDate;
    private int debtId;

    public PaymentPlanDTO(PaymentPlan paymentPlan) throws ParseException {
        this.id = paymentPlan.getId();
        this.amountToPay  = paymentPlan.getAmountToPay();
        this.startDate = paymentPlan.getStartDate();
        this.debtId = paymentPlan.getDebtId();
        if(paymentPlan.getInstallmentFrequency().equals(InstallmentFrequency.WEEKLY)) {
            this.installmentFrequency = InstallmentFrequency.WEEKLY;
        } else if(paymentPlan.getInstallmentFrequency().equals(InstallmentFrequency.BI_WEEKLY)) {
            this.installmentFrequency = InstallmentFrequency.BI_WEEKLY;
        } else {
            this.installmentFrequency = InstallmentFrequency.NA;
        }
    }
}
