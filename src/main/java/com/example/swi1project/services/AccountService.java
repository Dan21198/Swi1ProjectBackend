package com.example.swi1project.services;

import com.example.swi1project.model.Account;
import com.example.swi1project.model.Customer;

import java.util.List;

public interface AccountService {
    Account create(Account newAccount);
    Account getById(long id);
    void update(Account account) throws Exception;
    void delete(long id) throws Exception;
    List<Account> getAll();
    List<Account> getByName(String name);
}
