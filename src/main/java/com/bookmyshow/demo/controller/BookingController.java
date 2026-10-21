package com.bookmyshow.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    @GetMapping("/fuck")
    public String test() {
        return "Working Fuck";
    }
}
