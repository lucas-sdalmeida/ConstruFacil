package br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product.Product;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ProductSQliteDAO implements ProductDAO{
    @Override
    public void save(Product product) {
        String sql = "INSERT INTO product (id, name, quantity, purchasePrice, purchaseSale)  " +
                "VALUES (?, ?, ?, ?, ?)";
        try(PreparedStatement stmt = ConnectionFactory.createStatement(sql)){
            stmt.setLong(1, product.getId());
            stmt.setString(2, product.getName());
            stmt.setInt(3, product.getQuantity());
            stmt.setDouble(4, product.getPurchasePrice());
            stmt.setDouble(5, product.getPurchaseSale());
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public List<Product> search(int id) {
        return null;
    }
}
