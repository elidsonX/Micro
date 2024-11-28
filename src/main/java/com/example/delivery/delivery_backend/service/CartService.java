package com.example.delivery.delivery_backend.service;

import com.example.delivery.delivery_backend.model.CartItem;
import com.example.delivery.delivery_backend.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartItemRepository cartItemRepository;

    public List<CartItem> getCartItems() {
        return cartItemRepository.findAll();
    }

    public CartItem addToCart(CartItem item) {
        return cartItemRepository.save(item);
    }

    public void removeFromCart(String id) {
        cartItemRepository.deleteById(id);
    }
}