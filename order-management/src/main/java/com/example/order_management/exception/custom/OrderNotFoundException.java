package com.example.order_management.exception.custom;

//Business exceptions → Custom exceptions
//Technical exceptions → Framework handles

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(String msg){
        super(msg);
    }
}
