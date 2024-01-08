package com.example.bookshop.dao;

import com.example.bookshop.entity.Book;
import com.example.bookshop.entity.BookId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDao extends JpaRepository<Book, BookId> {
}
