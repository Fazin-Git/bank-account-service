package com.bank.accounts.controller;

import com.bank.accounts.dto.AccountBalance;
import com.bank.accounts.entity.Account;
import com.bank.accounts.service.AccountsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/accounts")
public class AccountsController {

    @Autowired
    private AccountsService accountService;

    @GetMapping("/{accountId}/balances")
    @ApiOperation(value = "Get account balance by id", response = Account.class, produces = "application/json")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
                            @ApiResponse(code = 404, message = "Account not found with ID")})
    public AccountBalance getBalance(
            @ApiParam(value = "ID related to the account", required = true) @PathVariable String accountId) {
        return accountService.retrieveAccountDetails(accountId);
    }
}
