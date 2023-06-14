package br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.util;

public class TransactionComponentFailedException extends RuntimeException {
    public TransactionComponentFailedException(String message) {
        super(message);
    }
}
