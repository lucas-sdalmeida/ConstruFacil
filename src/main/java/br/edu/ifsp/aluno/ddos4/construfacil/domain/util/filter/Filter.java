package br.edu.ifsp.aluno.ddos4.construfacil.domain.util.filter;

public interface Filter<T> {
    boolean applyTo(T value);
}
