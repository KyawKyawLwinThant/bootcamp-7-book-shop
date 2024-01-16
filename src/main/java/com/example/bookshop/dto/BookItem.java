package com.example.bookshop.dto;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class BookItem implements Serializable {
    private int id;
    private String isbn;
    private String title;
    private double price;
    private int quantity=1;
}
