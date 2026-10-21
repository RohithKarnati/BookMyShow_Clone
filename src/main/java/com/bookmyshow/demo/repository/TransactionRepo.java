package com.bookmyshow.demo.repository;

import com.bookmyshow.demo.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Integer> {
}
