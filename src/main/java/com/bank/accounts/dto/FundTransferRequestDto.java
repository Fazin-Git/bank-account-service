package com.bank.accounts.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class FundTransferRequestDto implements Serializable {
    @NotNull
    private String fromAccountNumber;

    @NotNull
    private String toAccountNumber;

    @NotNull
    private BigDecimal amount;
}
