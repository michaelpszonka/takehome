package com.trueaccord.takehome.dto;

import com.trueaccord.takehome.dao.model.Payment;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Data
@Getter @Setter
@NoArgsConstructor
public class PaymentDTO {
    private int paymentPlanId;
    private double amount;
    private String date;

    public PaymentDTO(Payment payment) {
        this.paymentPlanId = payment.getPaymentPlanId();
        this.amount = payment.getAmount();
        this.date = payment.getDate();
    }
}
