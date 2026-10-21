package com.bookmyshow.demo.models;


import com.bookmyshow.demo.models.constants.SeatType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
@Entity
public class Seat extends BaseModel{
    private String seatNumber;
    private int row;
    private int col;

    @Enumerated(EnumType.ORDINAL)
    private SeatType seatType;
}
