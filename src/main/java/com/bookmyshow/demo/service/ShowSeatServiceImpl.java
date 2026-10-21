package com.bookmyshow.demo.service;

import com.bookmyshow.demo.models.ShowSeat;
import com.bookmyshow.demo.repository.ShowSeatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowSeatServiceImpl implements IService {

    @Autowired
    private ShowSeatRepo showSeatRepo;

    public void saveShowSeat(ShowSeat showSeat) {
        showSeatRepo.save(showSeat);
    }


    @Override
    public String getUserName(String username) {
        return "Hello, " + username + "! Welcome to BookMyShow.";
    }



}
