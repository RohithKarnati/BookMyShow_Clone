package com.bookmyshow.demo.controller;

import com.bookmyshow.demo.dto.TicketRequestDTO;
import com.bookmyshow.demo.models.ShowSeat;
import com.bookmyshow.demo.models.Ticket;
import com.bookmyshow.demo.service.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TicketController {

    @Autowired
    TicketServiceImpl ticketService;

    public ResponseEntity<?> getTicketById(String ticketId) {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> createTicket(TicketRequestDTO ticketDetails) {

        long id = ticketDetails.getUser().getId();
        List<ShowSeat> showSeats = ticketDetails.getShowSeats();

        Ticket ticket = ticketService.bookTicket(id, showSeats);



        return ResponseEntity.ok().build();
    }
}
