package org.example.exception;

/**
 * Classe que representa uma exceção criada para o contexto deste sistema.
 * Deve ser lançada a exceção RegraDeNegocioException quando alguma lógica/regra do sistema
 * for quebrada
 *
 * @author carneiroangelojoaopedro@gmail.com
 */
public class RegraDeNegocioException extends RuntimeException {

    /**
     * Lança a exceção
     * @param mensagem Mensagem de erro
     */
    public RegraDeNegocioException(String mensagem){
        super(mensagem);
    }

}