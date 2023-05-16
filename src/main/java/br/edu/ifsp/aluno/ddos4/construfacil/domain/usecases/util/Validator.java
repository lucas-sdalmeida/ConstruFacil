package br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util;

public abstract class Validator<T> {
    public abstract Notification validate(T obj);

    public static boolean isNullOrEmpty(String string) {
        return string == null || string.length() == 0;
    }
}
