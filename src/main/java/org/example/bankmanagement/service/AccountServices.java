package org.example.bankmanagement.service;

import org.example.bankmanagement.entity.Account;

import java.util.List;

public interface AccountServices {
    Account createAccount(Account account);
    Account getAccountDetailsByAccountNumber(Long accountNumber);
    List<Account> getAllAccountDetails();
    Account depositMoney(Long accountNumber, Double amount);
    Account withdrawalAmount(Long accountNumber, Double amount);
    void  closeAccount(Long AccountNumber);
}