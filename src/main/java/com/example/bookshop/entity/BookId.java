package com.example.bookshop.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class BookId implements Serializable {
    private int id;
    private String isbn;
}
