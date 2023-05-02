package com.example.swi1project.services;

import com.example.swi1project.exception.RecordNotFoundException;
import com.example.swi1project.model.Customer;
import com.example.swi1project.model.Order;
import com.example.swi1project.repository.CustomerRepository;
import com.example.swi1project.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{
    private CustomerRepository customerRepository;

    private OrderRepository orderRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer create(Customer newCustomer) {
        Customer ret = customerRepository.save(newCustomer);
        return ret;
    }

    @Override
    public Customer getById(long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Customer not found."));
        return customer;
    }

    @Override
    public void update(Customer customer) throws RecordNotFoundException {
        Customer existingCustomer = customerRepository.findById(customer.getId())
                .orElseThrow(() -> new RecordNotFoundException("Customer not found."));

        existingCustomer.setName(customer.getName());
        existingCustomer.setSurName(customer.getSurName());
        existingCustomer.setAccountNumber(customer.getAccountNumber());
        existingCustomer.setPhone(customer.getPhone());
        existingCustomer.setEmail(customer.getEmail());

        customerRepository.save(existingCustomer);
    }

    @Override
    public void delete(long id) throws Exception {
        boolean exists = customerRepository.existsById(id);
        if(exists){
            customerRepository.deleteById(id);
        }else {
            throw new RecordNotFoundException("Customer not found.");
        }
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> getByName(String name) {
        return null;
    }
}
