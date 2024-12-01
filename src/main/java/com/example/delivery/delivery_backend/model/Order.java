package com.example.delivery.delivery_backend.model;

import javax.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    private String status;

    public void addItem(CartItem item) {
        items.add(item);
        item.setOrder(this);
    }

    public void removeItem(CartItem item) {
        items.remove(item);
        item.setOrder(null);
    }
}