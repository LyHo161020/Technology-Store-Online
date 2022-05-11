package com.company.services;

import com.company.model.Order;

import java.util.List;

public interface IOrderServices {
    List<Order> getOrders();

    void add(Order newOrder);

    void update();

    Order getOrderById(int id);

    boolean exist(int id);

    boolean checkDuplicateName(String name);

    boolean checkDuplicateId(int id);

    void remove(Order order);
}
