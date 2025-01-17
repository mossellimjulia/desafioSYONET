package org.example.domain.exceptions;

public class ErroEnvioEmailException extends RuntimeException{

    public ErroEnvioEmailException(String email) {
        super("Erro ao enviar e-mail para: " + email);
    }
}
