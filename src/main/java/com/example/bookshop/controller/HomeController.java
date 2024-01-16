package com.example.bookshop.controller;

import com.example.bookshop.service.BookService;
import com.example.bookshop.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final BookService bookService;
    private final CartService cartService;

    @RequestMapping({"/","home"})
    public String home(@RequestParam(value = "info",required = false)String clearCart
            , Model model, HttpSession session){
        if(clearCart!=null && clearCart.equals("clearCart")){
            cartService.clearCart();
            session.invalidate();
        }
        model.addAttribute("books",bookService.listBooks());
        return "home";
    }
}
