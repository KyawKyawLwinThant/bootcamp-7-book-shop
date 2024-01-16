package com.example.bookshop.service;

import com.example.bookshop.dao.BookBoughtDao;
import com.example.bookshop.dao.BookDao;
import com.example.bookshop.dao.CustomerDao;
import com.example.bookshop.dao.OrderItemDao;
import com.example.bookshop.dto.BookItem;
import com.example.bookshop.dto.CartItem;
import com.example.bookshop.entity.*;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerDao customerDao;
    private final BookDao bookDao;
    private final CartService cartService;
    private final OrderItemDao orderItemDao;
    private final BookBoughtDao bookBoughtDao;

    @Transactional
    public void saveCustomerOrderItems(Customer  customer){
        List<Order> orders=customerDao.fetchOrderByCustomerName(customer.getCustomerName());
        Order order=orders.get(0);
        Optional<BooksBought> book= bookBoughtDao.
                findBooksBoughtByCustomerName(customer.getCustomerName());

        if(!book.isPresent()) {
            BooksBought booksBought = new BooksBought();
            booksBought.setCustomerName(customer.getCustomerName());
            booksBought.setOrderId(order.getId());
            booksBought.setOrderDate(order.getOrderDate());
            List<BookItem> bookItems = getBookItems();

            booksBought.setBooks(bookItems);

            bookBoughtDao.save(booksBought);

        }
        else {
            BooksBought booksBought=book.get();
            List<BookItem> bookItems=booksBought.getBooks();
            List<BookItem> newBookItem=getBookItems();
            bookItems.addAll(newBookItem);
            booksBought.setBooks(bookItems);
            bookBoughtDao.saveAndFlush(booksBought);
        }
    }

    private List<BookItem> getBookItems() {
        List<BookItem> bookItems=new ArrayList<>();

        for(CartItem cartItem:cartService.getCartItems()){
            bookItems.add(toBook(cartItem));
        }
        return bookItems;
    }

    private BookItem toBook(CartItem cartItem){
        return new BookItem(
                cartItem.getId(),
                cartItem.getIsbn(),
                cartItem.getTitle(),
                cartItem.getPrice(),
                cartItem.getQuantity()
        );

    }
}
