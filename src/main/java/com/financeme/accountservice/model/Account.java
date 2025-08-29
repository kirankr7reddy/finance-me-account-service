package com.financeme.accountservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "account")  // matches your table in data.sql
public class Account {

    @Id
    private Long account_number;  // matches data.sql column

    private String account_holder_name;  // matches data.sql column
    private String policy_name;          // matches data.sql column
    private Double balance;              // matches data.sql column

    // Default constructor
    public Account() {}

    // Constructor with all fields
    public Account(Long account_number, String account_holder_name, String policy_name, Double balance) {
        this.account_number = account_number;
        this.account_holder_name = account_holder_name;
        this.policy_name = policy_name;
        this.balance = balance;
    }

    // Getters and Setters
    public Long getAccount_number() {
        return account_number;
    }

    public void setAccount_number(Long account_number) {
        this.account_number = account_number;
    }

    public String getAccount_holder_name() {
        return account_holder_name;
    }

    public void setAccount_holder_name(String account_holder_name) {
        this.account_holder_name = account_holder_name;
    }

    public String getPolicy_name() {
        return policy_name;
    }

    public void setPolicy_name(String policy_name) {
        this.policy_name = policy_name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "account_number=" + account_number +
                ", account_holder_name='" + account_holder_name + '\'' +
                ", policy_name='" + policy_name + '\'' +
                ", balance=" + balance +
                '}';
    }

    public Object getPolicyName() {
         return policy_name;
        
    }

	public void setPolicyName(Object policyName) {
        this.policy_name = (String) policyName;
		
	}

    public void setAccountHolderName(String accountHolderName) {
    this.account_holder_name = accountHolderName;
    
    }

    public void setAccountNumber(long accountNumber) {
    this.account_number = accountNumber;
       
    }
}



