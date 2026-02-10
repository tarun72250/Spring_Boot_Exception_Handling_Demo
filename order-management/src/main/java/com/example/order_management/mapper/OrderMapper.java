package com.example.order_management.mapper;

import com.example.order_management.dto.OrderRequestDTO;
import com.example.order_management.dto.OrderResponseDTO;
import com.example.order_management.entity.Order;
import com.example.order_management.enums.OrderStatus;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public Order toEntity(OrderRequestDTO dto){
        Order order = new Order();
        order.setProductName(dto.getProductName());
        order.setPrice(dto.getPrice());
        order.setStatus(OrderStatus.CREATED);
        return order;
    }

    public OrderResponseDTO toDTO(Order order){
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setId(order.getId());
        dto.setProductName(order.getProductName());
        dto.setPrice(order.getPrice());
        dto.setStatus(order.getStatus().name());
        return dto;
    }
}
