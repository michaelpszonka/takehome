package com.trueaccord.takehome.dao.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.Date;

@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @JsonProperty("payment_plan_id")
    @SerializedName("payment_plan_id")
    private int paymentPlanId;
    @JsonProperty("amount")
    @SerializedName("amount")
    private double amount;
    @JsonProperty("date")
    @SerializedName("date")
    private String date;

}
