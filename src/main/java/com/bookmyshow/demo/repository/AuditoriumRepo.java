package com.bookmyshow.demo.repository;

import com.bookmyshow.demo.models.Auditorium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditoriumRepo extends JpaRepository<Auditorium, Integer> {
}
