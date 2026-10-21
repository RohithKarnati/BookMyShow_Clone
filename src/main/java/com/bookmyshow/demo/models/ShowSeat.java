package com.bookmyshow.demo.models;

import com.bookmyshow.demo.models.constants.ShowSeatStatus;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ShowSeat extends BaseModel {
    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;

    @ManyToOne
    private Seat seat;

    @Enumerated(EnumType.ORDINAL)
    private ShowSeatStatus showSeatStatus;

    private double price;
}