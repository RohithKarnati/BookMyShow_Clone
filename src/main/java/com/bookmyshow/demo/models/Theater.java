package com.bookmyshow.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Theater extends BaseModel {
    private String theaterName;
    private String address;

    @OneToMany
    @JoinColumn(name = "theater_id")
    private List<Auditorium> auditoriums;
}
