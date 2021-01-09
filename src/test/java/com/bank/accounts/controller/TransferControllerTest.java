package com.bank.accounts.controller;

import com.bank.accounts.dto.FundTransferRequestDto;
import com.bank.accounts.dto.MoneyTransferResponse;
import com.bank.accounts.service.TransferService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TransferControllerTest {

    @InjectMocks
    TransferController transferController;

    @Mock
    TransferService transferService;

    @Test
    public void transferFundsTest(){
        MoneyTransferResponse moneyTransferResponse = new MoneyTransferResponse();
        moneyTransferResponse.setStatus("success");
        when(transferService.transferFunds(any())).thenReturn(moneyTransferResponse);
        MoneyTransferResponse moneyTransferResponse1 = transferController.transferFunds(any(FundTransferRequestDto.class));
        assertNotNull(moneyTransferResponse.getStatus(),moneyTransferResponse1.getStatus());
    }
}
