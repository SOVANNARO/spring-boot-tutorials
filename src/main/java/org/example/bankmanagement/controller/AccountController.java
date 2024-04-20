package org.example.bankmanagement.controller;

import org.example.bankmanagement.entity.Account;
import org.example.bankmanagement.service.AccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountServices services;

    @Autowired
    public AccountController(AccountServices services) {
        this.services = services;
    }

    @PostMapping("/create")
    public Account createAccount(@RequestBody Account account) {
        return services.createAccount(account);
    }

    @GetMapping("/{id}")
    public Account getAccountByAccountNumber(@PathVariable Long id) {
        return services.getAccountDetailsByAccountNumber(id);
    }

    @GetMapping("/list")
    public List<Account> getAllAccountDetails() {
        return services.getAllAccountDetails();
    }

    @PutMapping("deposit/{accountNumber}/{amount}")
    public Account depositMoney(@PathVariable Long accountNumber, @PathVariable Double amount) {
        return services.depositMoney(accountNumber, amount);
    }

    @PutMapping("withdraw/{accountNumber}/{amount}")
    public Account widthdrwaAmount(@PathVariable Long accountNumber, @PathVariable Double amount) {
        return services.withdrawalAmount(accountNumber, amount);
    }

    @DeleteMapping("close/{accountNumber}")
    public ResponseEntity<String> closeAccount(@PathVariable Long accountNumber) {
        services.closeAccount(accountNumber);
        return ResponseEntity.ok().body("Account Closed");
    }
}
