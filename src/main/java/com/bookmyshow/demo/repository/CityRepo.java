package com.bookmyshow.demo.repository;


import com.bookmyshow.demo.models.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepo extends JpaRepository<City, Integer> {
    City findByCityName(String name);
}
