package com.example.swi1project.services;

import com.example.swi1project.exception.RecordNotFoundException;
import com.example.swi1project.model.Account;
import com.example.swi1project.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AccountServiceImpl implements AccountService{
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account create(Account newAccount) {
        Account ret = accountRepository.save(newAccount);
        return ret;
    }

    @Override
    public Account getById(long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Account not found."));
        return account;
    }

    @Override
    public void update(Account account) throws RecordNotFoundException {
        Account existingAccount = accountRepository.findById(account.getId())
                .orElseThrow(() -> new RecordNotFoundException("Account not found."));

        existingAccount.setUserName(account.getUserName());
        existingAccount.setPassword(account.getPassword());

        Account updatedAccount = accountRepository.save(existingAccount);
        accountRepository.save(existingAccount);
    }

    @Override
    public void delete(long id) throws Exception {
        boolean exists = accountRepository.existsById(id);
        if(exists){
            accountRepository.deleteById(id);
        }else {
            throw new RecordNotFoundException("Account not found.");
        }
    }

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public List<Account> getByName(String name) {
        return null;
    }

}
