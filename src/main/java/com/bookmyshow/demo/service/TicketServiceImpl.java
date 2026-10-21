package com.bookmyshow.demo.service;

import com.bookmyshow.demo.exceptions.InvalidShowSeatSelectionException;
import com.bookmyshow.demo.models.Payment;
import com.bookmyshow.demo.models.ShowSeat;
import com.bookmyshow.demo.models.Ticket;
import com.bookmyshow.demo.models.User;
import com.bookmyshow.demo.models.constants.ShowSeatStatus;
import com.bookmyshow.demo.models.constants.TicketStatus;
import com.bookmyshow.demo.repository.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TicketServiceImpl implements IService {

    @Autowired
    private ShowSeatServiceImpl showSeatService;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private TicketRepo ticketRepo;

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public Ticket bookTicket(Integer userId, List<ShowSeat> showSeatList) {
        User user = userServiceImpl.getUserById(userId.toString());
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        for (ShowSeat showSeat : showSeatList) {
            if (!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)) {
                throw new InvalidShowSeatSelectionException("Seat :" + showSeat.getSeat().getSeatNumber() + " is not available for booking.");
            }
        }

        for(ShowSeat showSeat : showSeatList) {
            showSeat.setShowSeatStatus(ShowSeatStatus.LOCKED);
            showSeatService.saveShowSeat(showSeat);
        }

        // TODO: Ticket is Locked now Assuming Payment is also Done
        Payment payment = new Payment();

        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setShowSeats(showSeatList);
        ticket.setAmount(showSeatList.stream().mapToDouble(ShowSeat::getPrice).sum());
        ticket.setShow(showSeatList.get(0).getShow());
        ticket.setPayment(payment); // Assuming payment is done and we have a payment object
        ticket.setTicketStatus(TicketStatus.IN_PROGRESS);

        ticketRepo.save(ticket);

        for(ShowSeat showSeat : showSeatList) {
            showSeat.setShowSeatStatus(ShowSeatStatus.BOOKED);
            showSeatService.saveShowSeat(showSeat);
        }

        ticket.setTicketStatus(TicketStatus.CONFIRMED);

//        userServiceImpl.saveUser(user);
        return ticketRepo.save(ticket);


    }

    @Override
    public String getUserName(String userName) {
        return userName;
    }
}
