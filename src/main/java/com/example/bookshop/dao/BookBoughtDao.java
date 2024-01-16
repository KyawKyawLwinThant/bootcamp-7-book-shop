package com.example.bookshop.dao;

import com.example.bookshop.entity.BooksBought;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookBoughtDao extends JpaRepository<BooksBought,Integer> {
    Optional<BooksBought> findBooksBoughtByCustomerName(String customerName);
}
