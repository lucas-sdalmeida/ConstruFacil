package br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionFactory {
    private static Connection connection = null;
    private static PreparedStatement stmt = null;

    private static Connection getConnection(){
        Connection c = null;
        try {
            if(connection == null){
                c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            }else{
                c = connection;
            }
                
        } catch (Exception exception) {
            throw new CannotConnectToDatabaseException(exception.getMessage());
        }
        return c;
    }

    public static PreparedStatement getPreparedStatement(String sql) {
        try {
            connection = getConnection();
            stmt = connection.prepareStatement(sql);
        }
        catch (SQLException exception) {
            throw new CannotConnectToDatabaseException(exception.getMessage());
        }
        return stmt;
    }
}
