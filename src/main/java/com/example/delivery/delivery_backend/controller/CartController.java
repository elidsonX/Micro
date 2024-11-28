package com.example.delivery.delivery_backend.controller;

import com.example.delivery.delivery_backend.model.CartItem;
import com.example.delivery.delivery_backend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping
    public List<CartItem> getCartItems() {
        return cartService.getCartItems();
    }

    @PostMapping
    public CartItem addToCart(@RequestBody CartItem item) {
        return cartService.addToCart(item);
    }

    @DeleteMapping("/{id}")
    public void removeFromCart(@PathVariable String id) {
        cartService.removeFromCart(id);
    }
}