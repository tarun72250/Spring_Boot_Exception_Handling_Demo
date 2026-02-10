package com.example.order_management.service.impl;

import com.example.order_management.entity.Order;

public interface OrderService {

    Order getOrderById(Long id);

    Order createOrder(Order order);

    void deleteOrder(Long id);
}
