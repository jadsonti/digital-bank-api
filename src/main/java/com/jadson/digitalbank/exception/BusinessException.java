package com.jadson.digitalbank.exception;

public class BusinessException extends RuntimeException {

    public BusinessException(String mensagem) {
        super(mensagem);
    }
}
