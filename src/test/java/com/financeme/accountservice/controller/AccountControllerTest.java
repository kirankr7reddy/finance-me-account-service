package com.financeme.accountservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.financeme.accountservice.model.Account;
import com.financeme.accountservice.service.AccountService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")
    @MockBean
    private AccountService accountService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateAccount() throws Exception {
        Account account = new Account();
        account.setAccountNumber(9999L);
        account.setAccountHolderName("Test User");
        account.setPolicyName("Test Policy");
        account.setBalance(1234.56);

        Mockito.when(accountService.createAccount(Mockito.any(Account.class))).thenReturn(account);

        mockMvc.perform(post("/api/createAccount")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(account)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.account_holder_name").value("Test User"));
    }
}
