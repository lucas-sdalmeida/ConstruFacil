package br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
