package com.bank.accounts.service;

import com.bank.accounts.dto.AccountBalance;

public interface AccountsService {
    AccountBalance retrieveAccountDetails(String accountId);
}
