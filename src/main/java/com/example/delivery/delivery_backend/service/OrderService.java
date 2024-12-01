package com.example.delivery.delivery_backend.service;

import com.example.delivery.delivery_backend.model.CartItem;
import com.example.delivery.delivery_backend.model.Order;
import com.example.delivery.delivery_backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional
    public Order createOrder(List<CartItem> cartItems) {
        Order order = new Order();
        order.setStatus("PENDING");
        order = orderRepository.save(order);
        
        for (CartItem item : cartItems) {
            CartItem newItem = new CartItem();
            newItem.setName(item.getName());
            newItem.setDescription(item.getDescription());
            newItem.setPrice(item.getPrice());
            newItem.setImage(item.getImage());
            newItem.setQuantity(item.getQuantity());
            newItem.setObservacao(item.getObservacao());
            newItem.setOrder(order);
            order.getItems().add(newItem);
        }
        
        return orderRepository.save(order);
    }

    public String updateOrderStatus(Long id, String status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        order.setStatus(status);
        orderRepository.save(order);
        return status;
    }

    @Transactional
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        orderRepository.delete(order);
    }
}