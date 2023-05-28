package br.edu.ifsp.aluno.ddos4.construfacil.application.main;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.util.DataBaseBuilder;


public class Main {
    public static void main(String[] args) {
        DataBaseBuilder dbBuilder = new DataBaseBuilder();
        dbBuilder.buildDatabaseIfMissing();
    }
}
