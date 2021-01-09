package com.bank.accounts.exception;

import lombok.Data;

@Data
public class InSufficientBalanceException extends RuntimeException {
	private  String errorCode;
	private String message;

	public InSufficientBalanceException(String message, String errorCode) {
		this.message = message;
		this.errorCode = errorCode;
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}
}
