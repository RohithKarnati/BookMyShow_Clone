package com.bookmyshow.demo.models;


import com.bookmyshow.demo.models.constants.SeatType;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Seat extends BaseModel{
    private String seatNumber;
    private int row;
    private int col;
    private SeatType seatType;
}
