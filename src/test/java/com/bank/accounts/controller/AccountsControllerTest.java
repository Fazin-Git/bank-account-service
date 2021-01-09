package com.bank.accounts.controller;

import com.bank.accounts.dto.AccountBalance;
import com.bank.accounts.service.AccountsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class AccountsControllerTest {

    @InjectMocks
    AccountsController accountsController;

    @Mock
    AccountsService accountService;

    @Test
    public void retrieveAccountDetailsTest(){
        when(accountService.retrieveAccountDetails(anyString())).thenReturn(new AccountBalance());
        AccountBalance balance = accountsController.getBalance(anyString());
        assertNotNull(balance);
    }


}
