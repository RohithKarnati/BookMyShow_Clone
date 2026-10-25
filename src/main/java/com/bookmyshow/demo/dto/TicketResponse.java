package com.bookmyshow.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponse {
    private long id;
    private String movieName;
    private String showTime;
    private List<String> seatNumbers;
    private String auditoriumName;
    private double price;
}
