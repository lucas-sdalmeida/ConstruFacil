package br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase.Purchase;

import java.sql.Array;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PurchaseSQliteDAO implements PurchaseDAO{
    @Override
    public void save(Purchase purchase) {
        String sql = "INSERT INTO purchase (id, date, supplier, products) VALUES (?, ?, ?, ?)";
        try(PreparedStatement stmt = ConnectionFactory.createStatement(sql)){
            stmt.setLong(1, purchase.getId());
            //stmt.setDate(2, (Date) purchase.getDate());
            stmt.setLong(3, purchase.getSupplier().getID());
            //stmt.setArray(4, (Array) purchase.getProducts());
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Purchase purchase) {

    }

    @Override
    public Purchase serach(int id) {
        return null;
    }
}
