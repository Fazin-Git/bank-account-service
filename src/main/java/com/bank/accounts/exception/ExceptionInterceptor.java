package com.bank.accounts.exception;

import lombok.AllArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@AllArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionInterceptor extends ResponseEntityExceptionHandler {
    @ExceptionHandler(AccountNotExistException.class)
    public final ResponseEntity<Object> handleAllExceptions(AccountNotExistException ex) {
        AccountNotExistException exceptionResponse =
                new AccountNotExistException(
                        ex.getMessage(), ex.getErrorCode(), ex.getHttpStatus());
        return new ResponseEntity(exceptionResponse, ex.getHttpStatus());
    }

    @ExceptionHandler(InSufficientBalanceException.class)
    public final ResponseEntity<Object> handleAllExceptionsBalance(InSufficientBalanceException ex) {
        InSufficientBalanceException exceptionResponse =
                new InSufficientBalanceException(
                        ex.getMessage(), ex.getErrorCode());
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
