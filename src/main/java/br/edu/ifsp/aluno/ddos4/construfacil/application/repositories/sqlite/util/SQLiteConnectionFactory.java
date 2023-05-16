package br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.util;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.util.CannotConnectToDatabaseException;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;

public final class SQLiteConnectionFactory extends ConnectionFactory {
    private static Connection connection;

    @Override
    public Connection getConnection() {
        try {
            if(connection == null)
                connection = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            return connection;
        } catch (Exception exception) {
            throw new CannotConnectToDatabaseException(exception.getMessage());
        }
    }
}
