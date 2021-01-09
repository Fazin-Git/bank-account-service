package com.bank.accounts.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class AccountBalance implements Serializable {

    String accountNumber;

    BigDecimal currentBalance;
}
