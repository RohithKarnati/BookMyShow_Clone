package com.bookmyshow.demo.models;

import com.bookmyshow.demo.models.constants.FinalPaymentStatus;
import com.bookmyshow.demo.models.constants.PaymentMode;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Payment extends BaseModel {
    private double totalAmount;
    @Enumerated(EnumType.ORDINAL)
    private FinalPaymentStatus paymentStatus;

    @Enumerated(EnumType.ORDINAL)
    private PaymentMode paymentMode;

    @OneToMany
    private List<Transaction> transactions;
}
