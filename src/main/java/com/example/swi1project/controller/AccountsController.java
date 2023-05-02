package com.example.swi1project.controller;

import com.example.swi1project.model.Account;
import com.example.swi1project.services.AccountService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("http://localhost:3000")
@RestController
public class AccountsController {
    private AccountService accountService;

    public AccountsController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/accounts")
    public Account create(@Valid @RequestBody Account newAccount) {
        return accountService.create(newAccount);
    }

    @GetMapping("/accounts")
    public List<Account> getAll() {
        return accountService.getAll();
    }

    @GetMapping("/getAccountById/{id}")
    public Account get(@PathVariable("id") long id) {
        return accountService.getById(id);
    }

    @PutMapping("/accounts/{id}")
    public void update(@PathVariable("id") long id, @Valid @RequestBody Account account) throws Exception {
        account.setId(id);
        accountService.update(account);
    }

    @DeleteMapping("/account/{id}")
    public void delete(@PathVariable("id") long id) throws Exception {
        accountService.delete(id);
    }
}
