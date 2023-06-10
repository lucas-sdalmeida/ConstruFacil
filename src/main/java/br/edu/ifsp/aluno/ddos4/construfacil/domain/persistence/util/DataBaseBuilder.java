package br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.util;

public interface DataBaseBuilder {
    void buildDatabaseIfMissing();

    boolean isDatabaseAvailable();
}
