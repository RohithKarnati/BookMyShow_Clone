package com.bookmyshow.demo.repository;

import com.bookmyshow.demo.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowSeatRepo extends JpaRepository<ShowSeat, Integer> {
}
