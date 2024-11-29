package com.example.delivery.delivery_backend.service;

import com.example.delivery.delivery_backend.model.Order;
import com.example.delivery.delivery_backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order createOrder(Order order) {
        order.setStatus("PENDING");
        order.getItems().forEach(item -> item.setOrder(order));
        return orderRepository.save(order);
    }

    public Order updateOrderStatus(Long id, String status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        order.setStatus(status);
        return orderRepository.save(order);
    }
}