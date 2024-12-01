package com.example.delivery.delivery_backend.dto;

import lombok.Data;

@Data
public class CartItemDTO {
    private Long id;
    private String name;
    private String description;
    private double price;
    private String image;
    private int quantity;
    private String observacao;
}