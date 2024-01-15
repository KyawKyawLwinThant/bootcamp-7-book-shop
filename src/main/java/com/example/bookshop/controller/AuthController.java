package com.example.bookshop.controller;

import com.example.bookshop.entity.Customer;
import com.example.bookshop.entity.Order;
import com.example.bookshop.entity.PaymentMethod;
import com.example.bookshop.service.AuthService;
import com.example.bookshop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final CartService cartService;
    @RequestMapping("/register")
    public String register(Model model){
        model.addAttribute("customer",new Customer());
        return "register";
    }
    //public Order(LocalDate orderDate, String billingAddress, String shippingAddress, PaymentMethod paymentMethod, double totalAmount) {
    @PostMapping("/save-customer")
    public String saveCustomer(@RequestParam("billingAddress")String billingAddress,
                               @RequestParam("shippingAddress")String shippingAddress,
                               @RequestParam("payment")PaymentMethod method,
                               @ModelAttribute("totalPrice")double totalPrice,
                               Customer customer, BindingResult result){
        Order order=new Order(
                LocalDate.now(),
                billingAddress,
                shippingAddress,
                method,
                totalPrice
        );
        if(result.hasErrors()){
            return "register";
        }

        authService.register(customer,order);
        this.customer = customer;
        return "redirect:/auth/info";
    }

    private Customer customer;
    @GetMapping("/info")
    public ModelAndView checkoutInfo(ModelMap map,
                                     @ModelAttribute("totalPrice")double totalPrice){
        ModelAndView mv=new ModelAndView();
        mv.addObject("cartItems",cartService.getCartItems());
        mv.addObject("totalPrice",totalPrice);
        mv.addObject("customerInfo",authService
                .findCustomerInfoByCustomerName(customer.getCustomerName()));
        mv.setViewName("info");
        return mv;

    }
    //auth/login
    @GetMapping("/login")
    public String login(){

        return "login";
    }
    @ModelAttribute("totalPrice")
    public double totalAmount(){
        return cartService
                .getCartItems()
                .stream()
                .map(c -> c.getQuantity() * c.getPrice())
                .reduce((a,b) -> a + b)
                .get();
    }


}
