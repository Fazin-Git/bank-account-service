package com.bank.accounts.service;

import com.bank.accounts.dto.AccountBalance;
import com.bank.accounts.entity.Account;
import com.bank.accounts.repository.AccountRepository;
import com.bank.accounts.service.impl.AccountsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class AccountsServiceImplTest {

    @InjectMocks
    AccountsServiceImpl accountsService;

    @Mock
    AccountRepository accountRepository;

    @Test
    public void retrieveAccountDetailsTest(){
        Account account = new Account();
        account.setCurrentBalance(BigDecimal.valueOf(2000));
        when(accountRepository.findByAccountNumber(anyString())).thenReturn(Optional.of(account));
        AccountBalance accountBalance = accountsService.retrieveAccountDetails(anyString());
        assertEquals(accountBalance.getCurrentBalance(),BigDecimal.valueOf(2000));
    }

}
