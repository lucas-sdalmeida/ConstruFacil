package br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase.Purchase;

import java.sql.*;
import java.util.List;

public class PurchaseSQliteDAO implements PurchaseDAO{
    @Override
    public void save(Purchase purchase) {
        String sql = "INSERT INTO purchase (id, date, supplier, products) VALUES (?, ?, ?, ?)";
        try(PreparedStatement stmt = ConnectionFactory.createStatement(sql)){
            stmt.setLong(1, purchase.getId());
            stmt.setDate(2, (Date) purchase.getDate());
            stmt.setLong(3, purchase.getSupplier().getID());
            //stmt.setArray(4, (Array) purchase.getProducts());
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Purchase purchase) {
        String sql = "UPDATE purchase SET data=?, supplier=?, products=? WHERE id=?";
        try (PreparedStatement stmt = ConnectionFactory.createStatement(sql)){
            stmt.setDate(1, (Date) purchase.getDate());
            stmt.setLong(2, purchase.getSupplier().getID());
            //stmt.setLong(3, purchase.getProducts());
            stmt.setLong(4, purchase.getId());
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Purchase search(int id) {
        return null;
    }

    /*
    @Override
    public Purchase search(int id) {
        String sql = "SELECT * FROM purchase WHERE id=?";
        Purchase purchase = null;
        try(PreparedStatement stmt = ConnectionFactory.createStatement(sql)){
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                purchase = new Purchase(rs.getLong("id"), rs.getDate("Date"),
                        rs.getLong("supplier"), rs.getLong("product"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return purchase;
    }*/
}
