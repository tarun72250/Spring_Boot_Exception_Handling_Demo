package com.example.order_management.entity;


import com.example.order_management.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private Double price;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

}
