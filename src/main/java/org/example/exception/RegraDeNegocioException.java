package org.example.exception;

public class RegraDeNegocioException extends RuntimeException {

    public RegraDeNegocioException(String mensagem){
        super(mensagem);
    }

}