package com.financeme.accountservice.service;

import com.financeme.accountservice.model.Account;
import com.financeme.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account updateAccount(Long accountNumber, Account updatedAccount) {
        Optional<Account> existing = accountRepository.findById(accountNumber);
        if (existing.isPresent()) {
            Account account = existing.get();
            account.setPolicyName(updatedAccount.getPolicyName());
            account.setBalance(updatedAccount.getBalance());
            return accountRepository.save(account);
        } else {
            throw new RuntimeException("Account not found: " + accountNumber);
        }
    }

    public Account viewAccount(Long accountNumber) {
        return accountRepository.findById(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found: " + accountNumber));
    }

    public void deleteAccount(Long accountNumber) {
        accountRepository.deleteById(accountNumber);
    }

     public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
}
