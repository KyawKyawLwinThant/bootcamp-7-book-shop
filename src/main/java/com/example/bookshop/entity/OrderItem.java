package com.example.bookshop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;
    private int quantity;
    @OneToMany(mappedBy = "orderItem")
    private List<Book> books=
            new ArrayList<>();
    @JoinColumn(name = "order_id_fk")
    @ManyToOne
    private Order order;

    public void addBook(Book book){
        book.setOrderItem(this);
        this.books.add(book);
    }

}
