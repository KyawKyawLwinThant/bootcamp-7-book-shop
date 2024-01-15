package com.example.bookshop.service;

import com.example.bookshop.dao.BookDao;
import com.example.bookshop.dao.CustomerDao;
import com.example.bookshop.dao.OrderItemDao;
import com.example.bookshop.dto.CartItem;
import com.example.bookshop.entity.*;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerDao customerDao;
    private final BookDao bookDao;
    private final CartService cartService;
    private final OrderItemDao orderItemDao;
    @Transactional
    public void saveCustomerOrderItems(Customer customer){
        Customer customer1=customerDao
                .findCustomerByCustomerName(customer.getCustomerName())
                .orElseThrow(EntityExistsException::new);
        Order order=customer1.getOrders().getFirst();
        OrderItem orderItem=new OrderItem();
        orderItem.setOrder(order);
        int quantity = cartService.getCartItems()
                        .stream()
                                .map(CartItem::getQuantity)
                                        .mapToInt(Integer::intValue)
                                                .sum();
        orderItem.setQuantity(quantity);
        cartService.getCartItems()
                .stream()
                .map(c -> toBook(c))
                .forEach(b -> orderItem.addBook(b));
        orderItemDao.save(orderItem);
    }
    private Book toBook(CartItem cartItem){
        BookId bookId=new BookId();
        bookId.setId(cartItem.getId());
        bookId.setIsbn(cartItem.getIsbn());
        return bookDao.findById(bookId)
                .orElseThrow(EntityExistsException::new);
    }
}
