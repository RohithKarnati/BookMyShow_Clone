package com.bookmyshow.demo.models;

import com.bookmyshow.demo.models.constants.PaymentMode;
import com.bookmyshow.demo.models.constants.PaymentStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Transaction extends BaseModel {
    private double transactionAmount;

    @Enumerated(EnumType.ORDINAL)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.ORDINAL)
    private PaymentMode paymentMode;
    private LocalDateTime paymentTime;
    private String referenceId;

}
