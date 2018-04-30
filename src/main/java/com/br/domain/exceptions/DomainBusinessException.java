package com.br.domain.exceptions;

public class DomainBusinessException extends RuntimeException{
    public DomainBusinessException(String message){
        super(message);
    }
}