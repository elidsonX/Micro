package com.example.delivery.delivery_backend.controller;

import com.example.delivery.delivery_backend.model.OrderItem;
import com.example.delivery.delivery_backend.model.Order;
import com.example.delivery.delivery_backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody List<OrderItem> orderItems) {
        System.out.println("Received order items: " + orderItems);
        try {
            Order createdOrder = orderService.createOrder(orderItems);
            return ResponseEntity.ok(createdOrder);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateOrderStatus(@PathVariable Long id, @RequestBody Map<String, String> status) {
        String updatedStatus = orderService.updateOrderStatus(id, status.get("status"));
        return ResponseEntity.ok(updatedStatus);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}