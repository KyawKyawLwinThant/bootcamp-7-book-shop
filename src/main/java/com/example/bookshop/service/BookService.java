package com.example.bookshop.service;


import com.example.bookshop.dao.BookDao;
import com.example.bookshop.entity.Book;
import com.example.bookshop.entity.BookId;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookDao bookDao;

    public List<Book> listBooks(){
        return bookDao.findAll();
    }

    public Book findBookById(BookId bookId){
        return bookDao.findById(bookId)
                .orElseThrow(EntityNotFoundException::new);
    }


}
