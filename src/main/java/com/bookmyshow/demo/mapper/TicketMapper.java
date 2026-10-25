package com.bookmyshow.demo.mapper;

import com.bookmyshow.demo.dto.TicketResponse;
import com.bookmyshow.demo.models.ShowSeat;
import com.bookmyshow.demo.models.Ticket;

import java.util.stream.Collectors;

public class TicketMapper {

    public static TicketResponse toTicketResponse(Ticket ticket) {
        TicketResponse ticketResponse = new TicketResponse();

        ticketResponse.setId(ticket.getId());
        ticketResponse.setSeatNumbers(ticket.getShowSeats().stream().map(showSeat -> showSeat.getSeat().getSeatNumber()).collect(Collectors.toList()));
        ticketResponse.setAuditoriumName(ticket.getShow().getAuditorium().getName());
        ticketResponse.setMovieName(ticket.getShow().getMovie().getMovieName());
        ticketResponse.setShowTime(ticket.getShow().getStartTime().toString());
        ticketResponse.setPrice(ticket.getAmount());
        return ticketResponse;
    }
}
