package com.bookmyshow.demo.models;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseModel{
    private int number;
    private SeatType seatType;
//    private String seatType;
    private int row;
    private int col;
}
