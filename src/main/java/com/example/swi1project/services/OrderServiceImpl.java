package com.example.swi1project.services;

import com.example.swi1project.exception.RecordNotFoundException;
import com.example.swi1project.model.Car;
import com.example.swi1project.model.Customer;
import com.example.swi1project.model.Order;
import com.example.swi1project.repository.CustomerRepository;
import com.example.swi1project.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    private OrderRepository orderRepository;

    private CustomerRepository customerRepository;

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
                .orElseThrow(() -> new RecordNotFoundException("Order not found."));
        return order;
    }

    @Override
    public void update(Order order) throws RecordNotFoundException {
        Order existingOrder = orderRepository.findById(order.getId())
                .orElseThrow(() -> new RecordNotFoundException("Order not found."));

        existingOrder.setCustomer(order.getCustomer());
        //existingOrder.setCars(order.getCars());
        existingOrder.setDateOfOrder(order.getDateOfOrder());

        Double cost = calculateOrderCost(existingOrder);
        existingOrder.setCost(cost);

        orderRepository.save(existingOrder);
    }

    private Double calculateOrderCost(Order order) {
        Double cost = 0.0;
        for (Car car : order.getCars()) {
            cost += car.getPrice();
        }
        return cost;
    }

    @Override
    public void delete(long id) throws Exception {
        boolean exists = orderRepository.existsById(id);
        if(exists){
            orderRepository.deleteById(id);
        }else {
            throw new RecordNotFoundException("Order not found.");
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

    @Override
    public Customer findCustomerByOrderId(long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            return order.getCustomer();
        }
        return null;
    }
}
