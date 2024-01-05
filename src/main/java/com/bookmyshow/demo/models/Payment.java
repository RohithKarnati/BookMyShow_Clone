package com.bookmyshow.demo.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Payment extends BaseModel{
    private int refNum;
    private PaymentStatus paymentStatus;
    private int amount;
    private PaymentGateWayProvider paymentProvider;
    private PaymentMode paymentMode;
}
