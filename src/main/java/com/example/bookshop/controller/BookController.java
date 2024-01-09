package com.example.bookshop.controller;

import com.example.bookshop.dao.BookDao;
import com.example.bookshop.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;


//    public String listBooks(Model model){
//        model.addAttribute("books",
//                bookService.listBooks());
//
//    }
//
}
