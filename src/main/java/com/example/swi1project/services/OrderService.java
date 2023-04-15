package com.example.swi1project.services;

import com.example.swi1project.model.Order;

import java.util.List;

public interface OrderService {
    Order create(Order newOrder);
    Order getById(long id);
    void update(Order order) throws Exception;
    void delete(long id) throws Exception;
    List<Order> getAll();
    List<Order> getByName(String name);
}
