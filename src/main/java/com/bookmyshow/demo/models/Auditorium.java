package com.bookmyshow.demo.models;

import com.bookmyshow.demo.models.constants.Feature;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Auditorium extends BaseModel {
    private String name;

    @OneToMany
    private List<Seat> seats;

    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Feature> features;
}
