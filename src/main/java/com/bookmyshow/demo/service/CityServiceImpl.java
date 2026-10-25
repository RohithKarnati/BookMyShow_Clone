package com.bookmyshow.demo.service;

import com.bookmyshow.demo.models.City;
import com.bookmyshow.demo.repository.CityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl {

    @Autowired
    CityRepo cityRepo;

    public City getCityByName(long id) {
        return cityRepo.findById((int) id).orElseThrow(() -> new RuntimeException("City not found"));
    }

    public City saveCity(City city) {
        return cityRepo.save(city);
    }

    public City saveCityName(String name) {
        City city = new City();
        city.setCityName(name);
        return cityRepo.save(city);
    }

    public City getCityByName(String name) {
        return cityRepo.findByCityName(name);
    }

    public List<City> getAllCities() {
        return cityRepo.findAll();
    }

    public City getCityById(long id) {
        return cityRepo.findById((int) id).orElseThrow(() -> new RuntimeException("City not found"));
    }
}
