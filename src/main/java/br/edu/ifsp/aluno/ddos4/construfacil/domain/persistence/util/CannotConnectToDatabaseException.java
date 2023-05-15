package br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.util;

public class CannotConnectToDatabaseException extends RuntimeException {
    public CannotConnectToDatabaseException(String message) {
        super(message);
    }
}
