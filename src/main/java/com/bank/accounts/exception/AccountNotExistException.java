package com.bank.accounts.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class AccountNotExistException extends RuntimeException {
	private  String errorCode;
	private String message;
	private  HttpStatus httpStatus;


	public AccountNotExistException(String message, String errorCode,HttpStatus httpStatus) {
		this.message = message;
		this.errorCode = errorCode;
		this.httpStatus = httpStatus;
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}
}
