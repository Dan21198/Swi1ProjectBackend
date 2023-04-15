package com.example.swi1project.services;

import com.example.swi1project.exception.RecordNotFoundException;
import com.example.swi1project.model.Order;
import com.example.swi1project.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order create(Order newOrder) {
        Order ret = orderRepository.save(newOrder);
        return ret;
    }

    @Override
    public Order getById(long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Supplier not found."));
        return order;
    }

    @Override
    public void update(Order order) throws RecordNotFoundException {
        Order existingOrder = orderRepository.findById(order.getId())
                .orElseThrow(() -> new RecordNotFoundException("Account not found."));

        existingOrder.setCustomer(order.getCustomer());
        existingOrder.setCars(order.getCars());
        existingOrder.setCost(order.getCost());
        existingOrder.setDateOfOrder(order.getDateOfOrder());

        Order updatedCar = orderRepository.save(existingOrder);
        orderRepository.save(existingOrder);
    }

    @Override
    public void delete(long id) throws Exception {
        boolean exists = orderRepository.existsById(id);
        if(exists){
            orderRepository.deleteById(id);
        }else {
            throw new RecordNotFoundException("Supplier not found.");
        }
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getByName(String name) {
        return null;
    }
}
