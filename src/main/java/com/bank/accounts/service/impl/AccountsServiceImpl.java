package com.bank.accounts.service.impl;

import com.bank.accounts.dto.AccountBalance;
import com.bank.accounts.dto.ErrorCode;
import com.bank.accounts.entity.Account;
import com.bank.accounts.exception.AccountNotExistException;
import com.bank.accounts.repository.AccountRepository;
import com.bank.accounts.service.AccountsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AccountsServiceImpl implements AccountsService {

    private static final Logger log = LoggerFactory.getLogger(AccountsService.class);

    @Autowired
    AccountRepository accountRepository;

    @Override
    public AccountBalance retrieveAccountDetails(String accountId) {
        AccountBalance accountBalance = new AccountBalance();
        Account account = accountRepository.findByAccountNumber(accountId)
                .orElseThrow(() -> new AccountNotExistException("Account with id:" + accountId + " does not exist.", ErrorCode.ACCOUNT_ERROR, HttpStatus.NOT_FOUND));
        BeanUtils.copyProperties(account,accountBalance);
        log.info("Retrieve account balance completed for id {}",accountId);
        return accountBalance;
    }
}
