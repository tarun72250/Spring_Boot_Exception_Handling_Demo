package com.example.order_management.exception;

import com.example.order_management.exception.custom.OrderNotFoundException;
import com.example.order_management.exception.global.GlobalExceptionHandler;
import com.example.order_management.enums.ErrorCode;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void handleOrderNotFound_ShouldReturn404() {
        // Arrange
        OrderNotFoundException ex = new OrderNotFoundException("Order not found");

        // Act
        ResponseEntity<?> response = handler.handleOrderNotFound(ex);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void handleAllExceptions_ShouldReturn500() {
        // Arrange
        Exception ex = new RuntimeException("Unexpected error");

        // Act
        ResponseEntity<?> response = handler.handleAllExceptions(ex);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
