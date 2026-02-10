package com.example.order_management.controller;

import com.example.order_management.exception.custom.OrderNotFoundException;
import com.example.order_management.service.OrderService;
import com.example.order_management.mapper.OrderMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * SIMPLE CONTROLLER TEST - No Spring context required
 */
public class OrderControllerTest {

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
    void testOrderNotFoundExceptionThrown() {
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
        
        System.out.println("✅ Controller exception test passed");
        System.out.println("   Exception: " + exception.getMessage());
    }
}
