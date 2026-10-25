package com.bookmyshow.demo.controller;

import com.bookmyshow.demo.models.City;
import com.bookmyshow.demo.service.CityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CityController {

    @Autowired
    CityServiceImpl cityServiceImpl;

    @PostMapping("/city")
    public ResponseEntity<?> addCity(String name) {
        City city = cityServiceImpl.saveCityName(name);
        return ResponseEntity.ok(city);
    }

    @PostMapping("/city")
    public ResponseEntity<?> addCity(@RequestBody City city) {
        City savedCity = cityServiceImpl.saveCity(city);
        return ResponseEntity.ok(savedCity);
    }

    @GetMapping("/city")
    public ResponseEntity<?> getCities() {
        List<City> cities = cityServiceImpl.getAllCities();
        return ResponseEntity.ok(cities);
    }

    @GetMapping("/city/{name}")
    public ResponseEntity<?> getCityByName(@PathVariable String name) {
        City city = cityServiceImpl.getCityByName(name);
        return ResponseEntity.ok(city);
    }

    @GetMapping("/city/{id}")
    public ResponseEntity<?> getCityById(@PathVariable long id) {
        City city = cityServiceImpl.getCityById(id);
        return ResponseEntity.ok(city);
    }
}
