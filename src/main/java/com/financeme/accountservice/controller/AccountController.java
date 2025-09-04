package com.financeme.accountservice.controller;

import com.financeme.accountservice.model.Account;
import com.financeme.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/createAccount")
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @PutMapping("/updateAccount/{accountNo}")
    public Account updateAccount(@PathVariable Long accountNo, @RequestBody Account account) {
        return accountService.updateAccount(accountNo, account);
    }

    @GetMapping("/viewPolicy/{accountNo}")
    public Account viewAccount(@PathVariable Long accountNo) {
        return accountService.viewAccount(accountNo);
    }

    @DeleteMapping("/deletePolicy/{accountNo}")
    public String deleteAccount(@PathVariable Long accountNo) {
        accountService.deleteAccount(accountNo);
        return "Account with account number " + accountNo + " deleted successfully.";
    }

    @GetMapping("/")
    public String home() {
        return "✅FinanceMe App is running!";
    }

    @GetMapping("/viewAllAccounts")
    public List<Account> viewAllAccounts() {
        return accountService.getAllAccounts();
    }
}
    

