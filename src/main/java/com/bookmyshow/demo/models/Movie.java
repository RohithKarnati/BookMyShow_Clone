package com.bookmyshow.demo.models;

import com.bookmyshow.demo.models.constants.Feature;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Movie extends BaseModel {
    private String movieName;
    private String movieDescription;
    private String movieLanguage;

    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Feature> features;
}
