package br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.supplier.Supplier;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.util.ConnectionFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierSQliteDAO implements SupplierDAO{
    @Override
    public void save(Supplier supplier) {
        String sql = "INSERT INTO supplier (id, name, cpf, address, phoneNumber) " +
                "VALUES (?, ?, ?, ?, ?)";
        try(PreparedStatement stmt = ConnectionFactory.getPreparedStatement(sql)){
            stmt.setLong(1, supplier.getId());
            stmt.setString(2, supplier.getCorporateName());
            stmt.setString(5, supplier.getPhoneNumber());
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Supplier supplier) {
        String sql = "UPDATE supplier SET name=?, cpf=?, address=?, phoneNumber=? WHERE id=?";
        try(PreparedStatement stmt = ConnectionFactory.getPreparedStatement(sql)) {
            stmt.setString(1, supplier.getCorporateName());
            stmt.setString(4, supplier.getPhoneNumber());
            stmt.setLong(5, supplier.getId());
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Supplier search(int id) {
        String sql = "SELECT * FROM supplier WHERE id=?";
        Supplier supplier;
        try(PreparedStatement stmt = ConnectionFactory.getPreparedStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                supplier = new Supplier(rs.getLong("id"), 
                                rs.getString("corporateName"),
                                rs.getString("phoneNumber"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }
}
