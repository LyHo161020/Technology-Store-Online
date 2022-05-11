package com.company.services;

import com.company.model.OrderItem;

import java.util.List;

public interface IOrderItemServices {
    List<OrderItem> getOrderItems();

    void add(OrderItem newOrderItem);

    void update();

    OrderItem getOrderItemById(int id);
}
