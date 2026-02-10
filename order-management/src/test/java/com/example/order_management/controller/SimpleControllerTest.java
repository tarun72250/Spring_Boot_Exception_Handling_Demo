package com.example.order_management.controller;

import com.example.order_management.exception.custom.OrderNotFoundException;
import com.example.order_management.exception.global.GlobalExceptionHandler;
import com.example.order_management.service.OrderService;
import com.example.order_management.mapper.OrderMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Simple test without Spring context - just tests the controller logic
 */
public class SimpleControllerTest {

    private OrderController orderController;
    private OrderService orderService;
    private OrderMapper orderMapper;

    @BeforeEach
    void setUp() {
        orderService = mock(OrderService.class);
        orderMapper = mock(OrderMapper.class);
        orderController = new OrderController(orderService, orderMapper);
    }

    @Test
    void testOrderNotFoundException() {
        // Given
        Long orderId = 999L;
        when(orderService.getOrderById(orderId))
                .thenThrow(new OrderNotFoundException("Order not found with id: " + orderId));

        // When & Then
        OrderNotFoundException exception = assertThrows(
                OrderNotFoundException.class,
                () -> orderController.getOrder(orderId)
        );

        assertEquals("Order not found with id: 999", exception.getMessage());
        verify(orderService).getOrderById(orderId);
    }

    @Test
    void testGlobalExceptionHandler() {
        // Given
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        OrderNotFoundException exception = new OrderNotFoundException("Test error");

        // When
        ResponseEntity<?> response = handler.handleOrderNotFound(exception);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        
        System.out.println("✅ Controller exception handling test passed");
        System.out.println("Response: " + response.getBody());
    }
}
