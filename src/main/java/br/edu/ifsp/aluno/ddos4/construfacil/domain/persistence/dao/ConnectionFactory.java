package br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static ConnectionFactory INSTANCE;

    private static final String URL = "jdbc:sqlite:/caminho/para/o/arquivo.db";

    private ConnectionFactory() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver SQLite não encontrado", e);
        }
    }

    public static synchronized ConnectionFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConnectionFactory();
        }
        return INSTANCE;
    }

        public Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL);
        }
}