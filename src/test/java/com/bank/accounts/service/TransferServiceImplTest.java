package com.bank.accounts.service;


import com.bank.accounts.dto.FundTransferRequestDto;
import com.bank.accounts.dto.MoneyTransferResponse;
import com.bank.accounts.entity.Account;
import com.bank.accounts.exception.AccountNotExistException;
import com.bank.accounts.exception.InSufficientBalanceException;
import com.bank.accounts.repository.AccountRepository;
import com.bank.accounts.service.impl.TransferServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TransferServiceImplTest {

    @InjectMocks
    TransferServiceImpl transferService;

    @Mock
    AccountRepository accountRepository;

    @Test
    public void transferFundsTest(){
        FundTransferRequestDto fundTransferRequestDto = new FundTransferRequestDto();
        fundTransferRequestDto.setAmount(BigDecimal.valueOf(100));
        Account fromAccount = new Account();
        fromAccount.setCurrentBalance(BigDecimal.valueOf(2000));

        when(accountRepository.findByAccountNumber(any())).thenReturn(Optional.of(fromAccount));
        MoneyTransferResponse moneyTransferResponse = transferService.transferFunds(fundTransferRequestDto);
        verify(accountRepository, times(2)).save(any(Account.class));

    }

    @Test
    public void transferFundsInsufficientBalanceTest(){
        FundTransferRequestDto fundTransferRequestDto = new FundTransferRequestDto();
        fundTransferRequestDto.setAmount(BigDecimal.valueOf(100));
        Account fromAccount = new Account();
        fromAccount.setCurrentBalance(BigDecimal.valueOf(-2000));

        when(accountRepository.findByAccountNumber(any())).thenReturn(Optional.of(fromAccount));
        //MoneyTransferResponse moneyTransferResponse = transferService.transferFunds(fundTransferRequestDto);
        InSufficientBalanceException thrown = assertThrows(InSufficientBalanceException.class,
                ()->transferService.transferFunds(fundTransferRequestDto),
                "expected doNothing to throw,but it didn't");
        assertEquals(thrown.getErrorCode(),"ERR_CLIENT_002");
    }

    @Test
    public void transferFundsAccountNotExistExceptionTest(){
        FundTransferRequestDto fundTransferRequestDto = new FundTransferRequestDto();
        fundTransferRequestDto.setAmount(BigDecimal.valueOf(100));
        Account fromAccount = new Account();
        fromAccount.setCurrentBalance(BigDecimal.valueOf(2000));
        when(accountRepository.findByAccountNumber(any())).thenThrow(new AccountNotExistException("does not exist","ERR_CLIENT_002", HttpStatus.BAD_REQUEST));
        AccountNotExistException thrown = assertThrows(AccountNotExistException.class,
                ()->transferService.transferFunds(fundTransferRequestDto),
                "expected doNothing to throw,but it didn't");
        assertEquals("ERR_CLIENT_002",thrown.getErrorCode());

    }

}