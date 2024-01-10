package com.example.bookshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    private int id;
    private String isbn;
    private String title;
    private double price;
    private int quantity;
}
