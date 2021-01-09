package com.bank.accounts.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class MoneyTransferResponse implements Serializable {
    private String status;
}
