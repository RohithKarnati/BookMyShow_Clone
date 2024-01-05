package com.bookmyshow.demo.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel{
    private BookingStatus bookingStatus;
    private List<ShowSeat> showSeatList;
    private int amount;
    private List<Payment> payments;
}