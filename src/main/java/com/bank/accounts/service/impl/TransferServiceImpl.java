package com.bank.accounts.service.impl;

import com.bank.accounts.dto.ErrorCode;
import com.bank.accounts.dto.FundTransferRequestDto;
import com.bank.accounts.dto.MoneyTransferResponse;
import com.bank.accounts.entity.Account;
import com.bank.accounts.exception.AccountNotExistException;
import com.bank.accounts.exception.InSufficientBalanceException;
import com.bank.accounts.repository.AccountRepository;
import com.bank.accounts.service.AccountsService;
import com.bank.accounts.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional(isolation = Isolation.SERIALIZABLE)
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private static final Logger log = LoggerFactory.getLogger(AccountsService.class);

    @Autowired
    AccountRepository accountRepository;

    @Override
    public MoneyTransferResponse transferFunds(FundTransferRequestDto fundTransferRequestDto) {
        BigDecimal amountToTransfer = fundTransferRequestDto.getAmount();
        MoneyTransferResponse moneyTransferResponse = new MoneyTransferResponse();
        Account fromAccount = accountRepository.findByAccountNumber(fundTransferRequestDto.getFromAccountNumber())
                .orElseThrow(() -> new AccountNotExistException("Account with id:" + fundTransferRequestDto.getFromAccountNumber() + " does not exist.", ErrorCode.ACCOUNT_ERROR, HttpStatus.BAD_REQUEST));
        Account toAccount = accountRepository.findByAccountNumber(fundTransferRequestDto.getToAccountNumber())
                .orElseThrow(() -> new AccountNotExistException("Account with id:" + fundTransferRequestDto.getToAccountNumber() + " does not exist.", ErrorCode.ACCOUNT_ERROR, HttpStatus.BAD_REQUEST));

        if(fromAccount.getCurrentBalance().compareTo(fundTransferRequestDto.getAmount()) < 0) {
            log.info("Insufficient balance in account {}",fromAccount.getAccountId());
            throw new InSufficientBalanceException("Account with id:" + fromAccount.getAccountId() + " does not have enough balance to transfer.", ErrorCode.ACCOUNT_ERROR);
        }

        fromAccount.setCurrentBalance(fromAccount.getCurrentBalance().subtract(amountToTransfer));
        accountRepository.save(fromAccount);
        log.info("Deducted the fund from account {}",fromAccount.getAccountId());
        toAccount.setCurrentBalance(toAccount.getCurrentBalance().add(amountToTransfer));
        accountRepository.save(toAccount);
        log.info("Transferred funds from Account {} to {}",fromAccount.getAccountId(),toAccount.getAccountId());
        moneyTransferResponse.setStatus("success");
        return moneyTransferResponse;
    }
}
