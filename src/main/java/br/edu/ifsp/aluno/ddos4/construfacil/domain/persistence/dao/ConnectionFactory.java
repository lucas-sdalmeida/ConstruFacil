package br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionFactory implements AutoCloseable {
    private static PreparedStatement stmt = null;
    private static Connection conn  = null;

    public  static Connection createConnection() {
        try {
            if(conn == null)
                conn = DriverManager.getConnection("jdbc:sqlite:memory.bd");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return conn;
    }

    public static PreparedStatement createStatement(String sql){
        try {stmt = createConnection().prepareStatement(sql);}
        catch (SQLException e)
        { e.printStackTrace();}
        return stmt;
    }

    @Override
    public void close() throws Exception {
        if(conn != null)
            conn.close();
        if(stmt != null)
            stmt.close();
    }

}