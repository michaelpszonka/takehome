package com.trueaccord.takehome.dao.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentPlan {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "amount_to_pay")
    private double amountToPay;
    @Column(name = "installment_frequency")
    private double installmentFrequency;
    @Column(name = "installment_amount")
    private double installmentAmount;
    @Column(name = "start_date")
    private Date startDate;
//    @OneToOne
    @Column(name = "debt_id")
    private int debt_id;

}
