package br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class ConnectionFactory {
    private static Connection connection;

    public abstract Connection getConnection();

    public PreparedStatement getPreparedStatement(String sql) {
        try {
            return getConnection().prepareStatement(sql);
        }
        catch (SQLException exception) {
            throw new CannotConnectToDatabaseException(exception.getMessage());
        }
    }
}
