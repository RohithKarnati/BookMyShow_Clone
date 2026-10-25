package com.bookmyshow.demo.service;

import com.bookmyshow.demo.repository.AuditoriumRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditoriumServiceImpl {

    @Autowired
    AuditoriumRepo auditoriumRepo;


}
