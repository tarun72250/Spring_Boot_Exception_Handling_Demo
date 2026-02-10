package com.example.order_management.dto;

import lombok.Data;

//Controller ↔ DTO
//Service ↔ Entity
@Data
public class OrderResponseDTO {
    private Long id;
    private String productName;
    private Double price;
    private String status;
}
