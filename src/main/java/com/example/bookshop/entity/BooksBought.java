package com.example.bookshop.entity;

import com.example.bookshop.dto.BookItem;
import com.example.bookshop.dto.CartItem;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class BooksBought {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String customerName;
    private LocalDate orderDate;
    private int orderId;
    @ElementCollection
    private List<BookItem> books=
            new ArrayList<>();

    public void addBook(BookItem book){
        books.add(book);
    }
}
