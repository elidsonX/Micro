package com.example.delivery.delivery_backend.service;

import com.example.delivery.delivery_backend.model.OrderItem;
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
    public Order createOrder(List<OrderItem> orderItems) {
        System.out.println("Creating order with items: " + orderItems);
        
        Order order = new Order();
        order.setStatus("PENDING");
        
        for (OrderItem item : orderItems) {
            OrderItem newItem = new OrderItem();
            newItem.setName(item.getName());
            newItem.setDescription(item.getDescription());
            newItem.setPrice(item.getPrice());
            newItem.setImage(item.getImage());
            newItem.setQuantity(item.getQuantity());
            newItem.setObservacao(item.getObservacao());
            order.addItem(newItem);
        }
        
        order = orderRepository.save(order);
        System.out.println("Order created: " + order);
        
        return order;
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