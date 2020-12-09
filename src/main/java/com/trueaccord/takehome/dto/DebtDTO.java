package com.trueaccord.takehome.dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.trueaccord.takehome.dao.model.Debt;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter @Setter
public class DebtDTO {
    private int id;
    private double amount;
    @SerializedName("is_in_payment_plan")
    private boolean isInPaymentPlan;
    @SerializedName("remaining_amount")
    private double remainingAmount;
    @SerializedName("next_payment_due_date")
    private Date nextPaymentDueDate;

    public DebtDTO(Debt debt) {
        this.id = debt.getId();
        this.amount = debt.getAmount();
    }

    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        return gson.toJson(this);
    }
}
