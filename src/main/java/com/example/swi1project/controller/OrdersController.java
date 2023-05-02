package com.example.swi1project.controller;

import com.example.swi1project.model.Customer;
import com.example.swi1project.model.Order;
import com.example.swi1project.repository.CustomerRepository;
import com.example.swi1project.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("http://localhost:3000")
@RestController
public class OrdersController {
    private OrderService orderService;

    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public Order create(@Valid @RequestBody Order newOrder) {
        return orderService.create(newOrder);
    }

    @GetMapping("/orders")
    public List<Order> getAll() {
        return orderService.getAll();
    }

    @GetMapping("/orders/{id}")
    public Order get(@PathVariable("id") long id) {
        return orderService.getById(id);
    }

    @PutMapping("/orders/{id}")
    public void update(@PathVariable("id") long id, @Valid @RequestBody Order order) throws Exception {
        order.setId(id);
        orderService.update(order);
    }

    @DeleteMapping("/orders/{id}")
    public void delete(@PathVariable("id") long id) throws Exception {
        orderService.delete(id);
    }

    @GetMapping("/orders/{orderId}/customer")
    public ResponseEntity<Customer> findCustomerByOrderId(@PathVariable("orderId") long orderId) {
        Customer customer = orderService.findCustomerByOrderId(orderId);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(customer);
        }
    }
}
