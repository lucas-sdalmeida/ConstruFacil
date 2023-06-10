package br.edu.ifsp.aluno.ddos4.construfacil.application.main;

import br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.util.SQLiteDatabaseBuilder;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.util.DataBaseBuilder;


public class Main {
    public static void main(String[] args) {
        SQLiteDatabaseBuilder db = new SQLiteDatabaseBuilder();
        db.buildTables();
    }
}
