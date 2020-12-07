package com.trueaccord.takehome.dao.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Date;

@Entity
@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @Column(name = "payment_plan_id")
    private int paymentPlanId;
    @Column(name = "amount")
    private double amount;
    @Column(name = "date")
    private Date date;
//    @ManyToOne
//    private Debt debts;

}
