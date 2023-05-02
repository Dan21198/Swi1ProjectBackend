package com.example.swi1project.repository;

import com.example.swi1project.model.Customer;
import com.example.swi1project.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT c FROM Customer c JOIN c.orders o WHERE o.id = :orderId")
    public Customer findCustomerByOrderId(@Param("orderId") Long orderId);

}
