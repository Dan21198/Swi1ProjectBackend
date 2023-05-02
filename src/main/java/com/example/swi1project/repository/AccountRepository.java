package com.example.swi1project.repository;

import com.example.swi1project.model.Account;
import com.example.swi1project.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
