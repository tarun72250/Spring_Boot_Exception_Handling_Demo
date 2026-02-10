package com.example.order_management.controller;

import com.example.order_management.dto.OrderRequestDTO;
import com.example.order_management.dto.OrderResponseDTO;
import com.example.order_management.entity.Order;
import com.example.order_management.mapper.OrderMapper;
import com.example.order_management.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService,
                           OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrder(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(orderMapper.toDTO(order));
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@Valid @RequestBody OrderRequestDTO orderDTO) {
        Order order = orderMapper.toEntity(orderDTO);
        Order savedOrder = orderService.createOrder(order);
        return ResponseEntity.ok(orderMapper.toDTO(savedOrder));
    }
}
