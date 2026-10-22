package com.bookmyshow.demo.models;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "BMS_USERS")
public class User extends BaseModel {
    private String name;
    private String email;
    private String password;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Ticket> tickets;
}
