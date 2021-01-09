package com.bank.accounts.service;

import com.bank.accounts.dto.FundTransferRequestDto;
import com.bank.accounts.dto.MoneyTransferResponse;

public interface TransferService {
    MoneyTransferResponse transferFunds(FundTransferRequestDto moneyTransferRequest);
}
