package com.example.order_management.exception;

import com.example.order_management.exception.custom.OrderNotFoundException;
import com.example.order_management.exception.global.GlobalExceptionHandler;
import com.example.order_management.exception.model.ApiError;
import com.example.order_management.enums.ErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.*;

class SimpleExceptionHandlingTest {

    private GlobalExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        exceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void testOrderNotFoundException() {
        // Arrange
        String errorMessage = "Order not found with id: 999";
        OrderNotFoundException exception = new OrderNotFoundException(errorMessage);

        // Act
        ResponseEntity<ApiError> response = exceptionHandler.handleOrderNotFound(exception);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        
        ApiError apiError = response.getBody();
        assertNotNull(apiError);
        assertEquals(404, apiError.getStatus());
        assertEquals(errorMessage, apiError.getMessage());
        assertEquals(ErrorCode.ORDER_NOT_FOUND.name(), apiError.getErrorCode());
        assertNotNull(apiError.getTimestamp());
    }

    @Test
    void testGenericException() {
        // Arrange
        String errorMessage = "Unexpected error occurred";
        RuntimeException exception = new RuntimeException(errorMessage);

        // Act
        ResponseEntity<ApiError> response = exceptionHandler.handleAllExceptions(exception);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        
        ApiError apiError = response.getBody();
        assertNotNull(apiError);
        assertEquals(500, apiError.getStatus());
        assertEquals("Something went wrong. Please try again later", apiError.getMessage());
        assertEquals(ErrorCode.INTERNAL_ERROR.name(), apiError.getErrorCode());
        assertNotNull(apiError.getTimestamp());
    }
}
