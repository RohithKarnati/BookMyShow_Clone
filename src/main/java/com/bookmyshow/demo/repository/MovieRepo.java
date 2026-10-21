package com.bookmyshow.demo.repository;

import com.bookmyshow.demo.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Integer> {
}
