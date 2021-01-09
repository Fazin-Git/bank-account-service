package com.bank.accounts.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "account")
public class Account implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "account_d")
    private Long accountId;

    @Column(name = "account_number")
    String accountNumber;

    @Column(name = "current_balance")
    BigDecimal currentBalance;
}
