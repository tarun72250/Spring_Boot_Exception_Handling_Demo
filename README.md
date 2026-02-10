# Order Management - Enterprise Exception Handling

## 🎯 Project Overview

This project demonstrates **enterprise-grade exception handling** in Spring Boot with a focus on:
- ✅ Consistent error response structure
- ✅ No hardcoded values
- ✅ Easy maintenance and scalability
- ✅ Clean client integration
- ✅ Proper logging
- ✅ Production-ready architecture

## 🏗️ Architecture

### **Exception Handling Structure**
```
exception/
├── global/
│   └── GlobalExceptionHandler.java     # Centralized exception handling
├── custom/
│   ├── BusinessException.java          # Base business exception
│   ├── ResourceNotFoundException.java # Resource not found errors
│   └── ValidationException.java      # Validation errors
├── model/
│   └── ApiError.java               # Standard error response model
├── constants/
│   ├── ErrorCodes.java              # Centralized error codes
│   └── ErrorMessages.java           # Centralized error messages
└── enums/
    └── ErrorType.java               # Error type classification
```

### **Standard Flow**
```
Client → Controller → Service → Custom Exception → GlobalExceptionHandler → ApiError → Client
```

## 🚀 Getting Started

### **Prerequisites**
- Java 17+
- Maven 3.6+
- Spring Boot 3.5.10

### **Run Application**
```bash
# Using Maven Wrapper (Recommended)
./mvnw spring-boot:run

# Using IDE
# Right-click OrderManagementApplication.java → Run

# Using JAR
./mvnw clean package
java -jar target/order-management-0.0.1-SNAPSHOT.jar
```

## 📮 API Testing

### **Base URL**
```
http://localhost:8080/api/orders
```

### **Endpoints**

| Method | Endpoint | Description |
|---------|-----------|-------------|
| GET | `/api/orders/{id}` | Get order by ID |
| POST | `/api/orders` | Create new order |

### **Test Scenarios**

#### **1. Business Exception (Resource Not Found)**
```bash
curl -X GET http://localhost:8080/api/orders/999
```

**Response:**
```json
{
    "status": 404,
    "errorCode": "ORDER_NOT_FOUND",
    "message": "Order not found with id: Order with id: 999",
    "errorType": "BUSINESS",
    "path": "/api/orders/999",
    "timestamp": "2026-02-10T17:12:00.123"
}
```

#### **2. Validation Exception**
```bash
curl -X POST http://localhost:8080/api/orders \
  -H "Content-Type: application/json" \
  -d '{"productName":"","price":null}'
```

**Response:**
```json
{
    "status": 400,
    "errorCode": "VALIDATION_FAILED",
    "message": "productName: must not be blank",
    "errorType": "VALIDATION",
    "path": "/api/orders",
    "timestamp": "2026-02-10T17:12:00.456"
}
```

#### **3. Successful Order Creation**
```bash
curl -X POST http://localhost:8080/api/orders \
  -H "Content-Type: application/json" \
  -d '{"productName":"MacBook Pro","price":2499.99}'
```

**Response:**
```json
{
    "id": 1,
    "productName": "MacBook Pro",
    "price": 2499.99,
    "status": "CREATED"
}
```

#### **4. System Exception**
```bash
curl -X GET http://localhost:8080/api/orders/abc
```

**Response:**
```json
{
    "status": 500,
    "errorCode": "INTERNAL_SERVER_ERROR",
    "message": "An internal server error occurred. Please try again later.",
    "errorType": "SYSTEM",
    "path": "/api/orders/abc",
    "timestamp": "2026-02-10T17:12:01.789"
}
```

## 🧪 Testing

### **Run Tests**
```bash
# Run all tests
./mvnw test

# Run specific test class
./mvnw test -Dtest=IsolatedExceptionTest

# Run with coverage
./mvnw clean test jacoco:report
```

### **Test Coverage**
- ✅ Business exception handling
- ✅ Validation exception handling
- ✅ System exception handling
- ✅ ApiError model validation
- ✅ Error code constants
- ✅ Error type classification

## 📋 Enterprise Standards

### **✅ What We Implement**

#### **Consistent Error Response**
```json
{
    "status": 404,
    "errorCode": "ORDER_NOT_FOUND",
    "message": "Order not found with id: 999",
    "errorType": "BUSINESS",
    "path": "/api/orders/999",
    "timestamp": "2026-02-10T17:12:00.123"
}
```

#### **No Hardcoded Values**
- Error codes in `ErrorCodes.java` constants
- Error messages in `ErrorMessages.java` constants
- Error types in `ErrorType.java` enum
- HTTP status codes in `AppConstants.java`

#### **Clean Logging**
```java
// Business exceptions - WARN level
log.warn("Business exception occurred: {} - {}", ex.getErrorCode(), ex.getMessage());

// System exceptions - ERROR level with stack trace
log.error("Unexpected error occurred at {}: {}", request.getRequestURI(), ex.getMessage(), ex);
```

#### **Proper Error Classification**
- `BUSINESS` - Business logic violations
- `VALIDATION` - Input validation failures  
- `SYSTEM` - Internal system errors

### **❌ What We Avoid**

- ❌ Hardcoded error messages
- ❌ Try-catch in every controller
- ❌ Returning String error messages
- ❌ Exposing stack traces to clients
- ❌ Using RuntimeException directly
- ❌ Magic strings for error codes

## 🔧 Configuration

### **Application Properties**
```properties
# Server Configuration
server.port=8080
server.servlet.context-path=/

# Logging Configuration
logging.level.com.example.order_management=INFO
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} - %msg%n

# Database Configuration (if needed)
spring.datasource.url=jdbc:mysql://localhost:3306/order_db
spring.datasource.username=root
spring.datasource.password=password
```

## 📊 Error Codes Reference

### **Business Errors**
| Code | Description | HTTP Status |
|------|-------------|--------------|
| `ORDER_NOT_FOUND` | Order not found | 404 |
| `INVALID_ORDER_STATUS` | Invalid order status | 400 |
| `DUPLICATE_ORDER` | Duplicate order | 409 |
| `ORDER_PROCESSING_ERROR` | Order processing failed | 500 |

### **Validation Errors**
| Code | Description | HTTP Status |
|------|-------------|--------------|
| `VALIDATION_FAILED` | Input validation failed | 400 |
| `INVALID_INPUT` | Invalid input provided | 400 |
| `MISSING_REQUIRED_FIELD` | Required field missing | 400 |

### **System Errors**
| Code | Description | HTTP Status |
|------|-------------|--------------|
| `INTERNAL_SERVER_ERROR` | Internal server error | 500 |
| `DATABASE_ERROR` | Database operation failed | 500 |
| `NETWORK_ERROR` | Network operation failed | 503 |

## 🎯 Best Practices

### **For Developers**
1. **Always extend `BusinessException`** for custom business errors
2. **Use constants** from `ErrorCodes` and `ErrorMessages`
3. **Categorize errors** using proper `ErrorType`
4. **Never expose stack traces** to clients
5. **Log appropriately** - WARN for business, ERROR for system

### **For Client Integration**
1. **Handle error response structure** consistently
2. **Check `errorType`** for error categorization
3. **Use `errorCode`** for programmatic handling
4. **Display `message`** to users
5. **Log `path` and `timestamp`** for debugging

## 🚀 Production Deployment

### **Docker Support**
```dockerfile
FROM openjdk:17-jdk-slim
COPY target/order-management-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

### **Environment Variables**
```bash
export SERVER_PORT=8080
export SPRING_PROFILES_ACTIVE=prod
export LOGGING_LEVEL_ROOT=WARN
```

## 📝 Contributing

1. Follow the established exception handling patterns
2. Add new error codes to `ErrorCodes.java`
3. Add new error messages to `ErrorMessages.java`
4. Write tests for new exception types
5. Update documentation

## 📄 License

This project is for educational purposes to demonstrate enterprise exception handling patterns in Spring Boot.

---

## 🎉 Summary

This project showcases **production-ready exception handling** with:
- 🏗️ **Scalable architecture** for large applications
- 🔒 **Type-safe error handling** with enums and constants
- 📊 **Consistent API responses** for better client integration
- 🧹 **Clean code** with no hardcoded values
- 📝 **Comprehensive logging** for debugging and monitoring
- 🧪 **Full test coverage** for reliability

**Ready for enterprise production use!** 🚀
