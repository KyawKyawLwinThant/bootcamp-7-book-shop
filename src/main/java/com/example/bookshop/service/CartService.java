package com.example.bookshop.service;

import com.example.bookshop.dto.CartItem;
import com.example.bookshop.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartBean cartBean;

    public void addToCart(Book book){
        cartBean.addCartItem(toCartItem(book));
    }

    public Set<CartItem> getCartItems(){
        return cartBean.getCartItems();
    }

    public Integer cartSize(){
        return cartBean.cartSize();
    }

    private CartItem toCartItem(Book book){
        List<Integer> list=new ArrayList<>();
        list.add(1);
        return new CartItem(
                book.getId(),
                book.getIsbn(),
                book.getTitle(),
                book.getPrice(),
                1,
                list
        );
    }

    public void deleteCartItem(int id, String isbn) {
        cartBean.deleteCartItem(id,isbn);
    }

    public void clearCart() {
        cartBean.clearCart();
    }
}
