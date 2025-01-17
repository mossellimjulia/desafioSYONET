package org.example.domain.exceptions;

public class EmailJaCadastradoException extends RuntimeException{

    public EmailJaCadastradoException(String mensagem) {
        super(mensagem);
    }
}
