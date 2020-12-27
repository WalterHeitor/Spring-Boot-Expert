package com.softWalter.restApi.controller;

import com.softWalter.exception.RegraNegocioException;
import com.softWalter.restApi.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //substitui @ControllerAdvice e @RequestoBody
public class ApplicationControllerAdvice {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handlerRegraNegocioException(RegraNegocioException ex){
        String messageErro = ex.getMessage();
        return new ApiErrors(messageErro);
    }
}
