package com.example.bookshop.dao;

import com.example.bookshop.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherDao extends JpaRepository<Publisher,Integer> {
}
