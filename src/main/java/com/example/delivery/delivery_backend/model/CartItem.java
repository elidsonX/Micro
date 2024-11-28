package com.example.delivery.delivery_backend.model;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private double price;
    private String image;
    private int quantity;
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}