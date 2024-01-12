package com.example.bookshop.dao;

import com.example.bookshop.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerDao extends JpaRepository<Customer,Integer> {

    Optional<Customer> findCustomerByCustomerName(String customerName);
}
