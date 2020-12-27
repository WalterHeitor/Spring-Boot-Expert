package com.softWalter.restApi.controller;

import com.softWalter.exception.PedidoNaoEncontradoException;
import com.softWalter.exception.RegraNegocioException;
import com.softWalter.restApi.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice //substitui @ControllerAdvice e @RequestoBody
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handlerRegraNegocioException(RegraNegocioException ex){
        String messageErro = ex.getMessage();
        return new ApiErrors(messageErro);
    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiErrors handlePedidoNotfoundException(PedidoNaoEncontradoException ex){
        return new ApiErrors(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleMethodValidException(MethodArgumentNotValidException ex){
        List<String> errors = ex.getBindingResult().getAllErrors()
                .stream()
                .map(objectError ->
                        objectError.getDefaultMessage())
                .collect(Collectors.toList());
        return new ApiErrors(errors);
    }
}
