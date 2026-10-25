package com.bookmyshow.demo.dto;

import com.bookmyshow.demo.models.ShowSeat;
import com.bookmyshow.demo.models.User;
import lombok.Data;

import java.util.List;

@Data
public class TicketRequestDTO {

    private User user;
    private List<ShowSeat> showSeats;
}
