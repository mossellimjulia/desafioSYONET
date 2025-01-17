package org.example.domain.exceptions;

public class DadosInvalidosException extends RuntimeException{

    public DadosInvalidosException(String mensagem) {
        super(mensagem);
    }
}
