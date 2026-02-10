package com.example.order_management.exception;

import com.example.order_management.exception.custom.OrderNotFoundException;
import com.example.order_management.exception.global.GlobalExceptionHandler;
import com.example.order_management.exception.model.ApiError;
import com.example.order_management.enums.ErrorCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Basic test without Spring context - just tests the exception handler logic
 */
public class BasicExceptionTest {

    @Test
    public void testOrderNotFoundExceptionHandling() {
        // Given
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        String errorMessage = "Order not found with id: 123";
        OrderNotFoundException exception = new OrderNotFoundException(errorMessage);

        // When
        var response = handler.handleOrderNotFound(exception);

        // Then
        assertNotNull(response, "Response should not be null");
        assertEquals(404, response.getStatusCodeValue(), "Status should be 404");
        
        ApiError apiError = response.getBody();
        assertNotNull(apiError, "ApiError should not be null");
        assertEquals(404, apiError.getStatus(), "ApiError status should be 404");
        assertEquals(errorMessage, apiError.getMessage(), "Error message should match");
        assertEquals(ErrorCode.ORDER_NOT_FOUND.name(), apiError.getErrorCode(), "Error code should match");
        assertNotNull(apiError.getTimestamp(), "Timestamp should not be null");
        
        System.out.println("✅ OrderNotFoundException test passed");
        System.out.println("Response: " + apiError);
    }

    @Test
    public void testGenericExceptionHandling() {
        // Given
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        RuntimeException exception = new RuntimeException("Database connection failed");

        // When
        var response = handler.handleAllExceptions(exception);

        // Then
        assertNotNull(response, "Response should not be null");
        assertEquals(500, response.getStatusCodeValue(), "Status should be 500");
        
        ApiError apiError = response.getBody();
        assertNotNull(apiError, "ApiError should not be null");
        assertEquals(500, apiError.getStatus(), "ApiError status should be 500");
        assertEquals("Something went wrong. Please try again later", apiError.getMessage(), "Generic error message should match");
        assertEquals(ErrorCode.INTERNAL_ERROR.name(), apiError.getErrorCode(), "Error code should match");
        assertNotNull(apiError.getTimestamp(), "Timestamp should not be null");
        
        System.out.println("✅ Generic Exception test passed");
        System.out.println("Response: " + apiError);
    }

    @Test
    public void testCustomExceptionCreation() {
        // Given & When
        OrderNotFoundException exception = new OrderNotFoundException("Test message");

        // Then
        assertNotNull(exception, "Exception should not be null");
        assertEquals("Test message", exception.getMessage(), "Exception message should match");
        
        System.out.println("✅ Custom exception creation test passed");
    }
}
