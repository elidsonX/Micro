package com.example.delivery.delivery_backend.service;

import com.example.delivery.delivery_backend.model.CartItem;
import com.example.delivery.delivery_backend.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public List<CartItem> getCartItems() {
        return cartRepository.findAll();
    }

    public CartItem addToCart(CartItem item) {
        return cartRepository.save(item);
    }

    public void removeFromCart(Long id) {
        cartRepository.deleteById(id);
    }

    public void clearCart() {
        cartRepository.deleteAll();
    }
}