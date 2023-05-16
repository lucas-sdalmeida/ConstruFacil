package br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util;

public class EntityAlreadyExistsException extends RuntimeException {
    public EntityAlreadyExistsException(String message) {
        super(message);
    }
}
