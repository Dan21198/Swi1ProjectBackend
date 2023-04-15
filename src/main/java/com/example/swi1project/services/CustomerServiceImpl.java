package com.example.swi1project.services;

import com.example.swi1project.exception.RecordNotFoundException;
import com.example.swi1project.model.Customer;
import com.example.swi1project.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService{
    private CustomerRepository customerRepository;

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
                .orElseThrow(() -> new RecordNotFoundException("Supplier not found."));
        return customer;
    }

    @Override
    public void update(Customer customer) throws RecordNotFoundException {
        Customer existingCustomer = customerRepository.findById(customer.getId())
                .orElseThrow(() -> new RecordNotFoundException("Account not found."));

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
            throw new RecordNotFoundException("Supplier not found.");
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
