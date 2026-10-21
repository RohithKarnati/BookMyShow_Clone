package com.bookmyshow.demo.repository;

import com.bookmyshow.demo.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepo extends JpaRepository<Seat, Integer> {
}
