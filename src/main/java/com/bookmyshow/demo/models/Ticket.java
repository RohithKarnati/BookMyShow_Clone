package com.bookmyshow.demo.models;

import com.bookmyshow.demo.models.constants.TicketStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Ticket extends BaseModel {
    @OneToMany
    @JoinColumn(name = "ticket_id")
    private List<ShowSeat> showSeats;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private double amount;

    @OneToOne
    private Payment payment;
    @ManyToOne
    private Show show;

    @Enumerated(EnumType.ORDINAL)
    private TicketStatus ticketStatus;
}
