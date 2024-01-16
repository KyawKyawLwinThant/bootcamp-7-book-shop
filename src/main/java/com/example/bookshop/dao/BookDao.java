package com.example.bookshop.dao;

import com.example.bookshop.dto.OrderItemInfo;
import com.example.bookshop.entity.Book;
import com.example.bookshop.entity.BookId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookDao extends JpaRepository<Book, BookId> {

}
