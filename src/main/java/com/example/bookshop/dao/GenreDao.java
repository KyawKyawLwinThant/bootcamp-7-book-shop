package com.example.bookshop.dao;

import com.example.bookshop.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreDao extends JpaRepository<Genre,Integer> {
}
