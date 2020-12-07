package com.trueaccord.takehome.dao.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Debt {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "amount")
    private double amount;
}
