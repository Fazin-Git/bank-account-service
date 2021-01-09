package com.bank.accounts.controller;

import com.bank.accounts.dto.FundTransferRequestDto;
import com.bank.accounts.dto.MoneyTransferResponse;
import com.bank.accounts.service.TransferService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Api("Transfer operations")
@RequestMapping("/api/v1/transfer")
public class TransferController {

    @Autowired
    TransferService transferService;

    @ApiResponses({
            @ApiResponse(code=200,message = "Money transfer completed successfully."),
            @ApiResponse(code=401,message = "Unauthorized access."),
            @ApiResponse(code=400,message = "Bad request."),
            @ApiResponse(code=500,message = "Internal server error.")
    })
    @PostMapping(consumes = {"application/json"})
    public MoneyTransferResponse transferFunds(@RequestBody @Valid FundTransferRequestDto fundTransferRequestDto){
        return transferService.transferFunds(fundTransferRequestDto);
    }
}
