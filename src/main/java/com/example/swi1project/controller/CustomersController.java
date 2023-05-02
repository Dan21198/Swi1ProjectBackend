package com.example.swi1project.controller;

import com.example.swi1project.model.Customer;
import com.example.swi1project.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("http://localhost:3000")
@RestController
public class CustomersController {
    private CustomerService customerService;

    public CustomersController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customers")
    public Customer create(@Valid @RequestBody Customer newCustomer) {
        return customerService.create(newCustomer);
    }

    @GetMapping("/customers")
    public List<Customer> getAll() {
        return customerService.getAll();
    }

    @GetMapping("/customers/{id}")
    public Customer get(@PathVariable("id") long id) {
        return customerService.getById(id);
    }

    @GetMapping("/customers/search/{name}")
    public List<Customer> searchByName(@PathVariable("name") String name) {
        return customerService.getByName(name);
    }

    @PutMapping("/customers/{id}")
    public void update(@PathVariable("id") long id, @Valid @RequestBody Customer customer) throws Exception {
        if (customer.getId() != id) {
            throw new Exception("ID in path does not match ID in customer object");
        }
        customerService.update(customer);
    }

    @DeleteMapping("/customers/{id}")
    public void delete(@PathVariable("id") long id) throws Exception {
        customerService.delete(id);
    }
}
