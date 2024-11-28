package com.example.delivery.delivery_backend.model;

import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "DISH")
@Data
public class Dish {
    @Id
    private String id;
    private String name;
    private String description;
    private double price;
    private String image;
}