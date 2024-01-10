package com.example.bookshop.service;

import com.example.bookshop.dto.CartItem;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class CartBean {
    private List<CartItem> cartItems=
            new ArrayList<>();

    public void addCartItem(CartItem cartItem){
        cartItems.add(cartItem);
    }

    public Integer cartSize(){
        return cartItems.size();
    }
}
