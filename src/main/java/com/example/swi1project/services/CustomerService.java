package com.example.swi1project.services;

import com.example.swi1project.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CustomerService {
    Customer create(Customer newCustomer);
    Customer getById(long id);
    void update(Customer customer) throws Exception;
    void delete(long id) throws Exception;
    List<Customer> getAll();
    List<Customer> getByName(String name);
}
