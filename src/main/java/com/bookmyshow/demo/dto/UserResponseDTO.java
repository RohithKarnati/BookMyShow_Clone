package com.bookmyshow.demo.dto;

import com.bookmyshow.demo.models.Ticket;
import lombok.Data;

import java.util.List;

@Data
public class UserResponseDTO implements IResponseDTO {
    private Long id;
    private String name;
    private String email;
    private List<Ticket> tickets;
}
