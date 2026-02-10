package com.example.order_management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderRequestDTO {

    @NotBlank
    private String productName;

    @NotNull
    private Double price;
}
