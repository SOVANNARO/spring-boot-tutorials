package org.example.bankmanagement.service;

import org.example.bankmanagement.entity.Account;
import org.example.bankmanagement.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountServices {
    private final AccountRepository repository;

    @Autowired
    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public Account createAccount(Account account) {
        return repository.save(account);
    }

    @Override
    public Account getAccountDetailsByAccountNumber(Long accountNumber) {
        Optional<Account> account = repository.findById(accountNumber);
        if (account.isEmpty()) {
            throw new RuntimeException("Account not found");
        }
        return account.get();
    }

    @Override
    public List<Account> getAllAccountDetails() {
        return repository.findAll();
    }

    @Override
    public Account depositMoney(Long accountNumber, Double amount) {
        Optional<Account> account = repository.findById(accountNumber);
        if (account.isEmpty()) {
            throw new RuntimeException("Account not found");
        }
        account.get().setAccount_balance(account.get().getAccount_balance() + amount);
        return repository.save(account.get());
    }

    @Override
    public Account withdrawalAmount(Long accountNumber, Double amount) {
        Optional<Account> account = repository.findById(accountNumber);
        if (account.isEmpty()) {
            throw new RuntimeException("Account not found");
        }
        account.get().setAccount_balance(account.get().getAccount_balance() - amount);
        return repository.save(account.get());
    }

    @Override
    public void closeAccount(Long AccountNumber) {
        Optional<Account> account = repository.findById(AccountNumber);
        if (account.isEmpty()) {
            throw new RuntimeException("Account not found");
        }
        account.get().setAccount_balance(0.0);
        repository.save(account.get());
        repository.delete(account.get());
    }
}
