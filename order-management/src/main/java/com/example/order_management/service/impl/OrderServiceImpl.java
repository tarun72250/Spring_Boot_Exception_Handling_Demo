package com.example.order_management.service.impl;

import com.example.order_management.entity.Order;
import com.example.order_management.exception.custom.ResourceNotFoundException;
import com.example.order_management.repository.OrderRepository;
import com.example.order_management.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @Override
    public Order getOrderById(Long id) {
      return orderRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Order", id));
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = getOrderById(id);
        orderRepository.delete(order);
    }
}
