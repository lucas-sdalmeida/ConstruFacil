package br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductSQliteDAO implements ProductDAO{
    @Override
    public void save(Product product) {
        String sql = "INSERT INTO product (id, name, quantity, purchasePrice, purchaseSale)  " +
                "VALUES (?, ?, ?, ?, ?)";
        try(PreparedStatement stmt = ConnectionFactory.createStatement(sql)){
            stmt.setLong(1, product.getId());
            stmt.setString(2, product.getName());
            stmt.setInt(3, product.getQuantity());
            stmt.setDouble(4, product.getDefaultPurchasePrice());
            stmt.setDouble(5, product.getDefaultSalePrice());
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Product product) {
        String sql = "UPDATE product SET name=?, quantity=?, purchasePrice=?, purchaseSale=?" +
                "WHERE id=?";
        try(PreparedStatement stmt = ConnectionFactory.createStatement(sql)){
            stmt.setString(1, product.getName());
            stmt.setInt(2, product.getQuantity());
            stmt.setDouble(3, product.getDefaultPurchasePrice());
            stmt.setDouble(4, product.getDefaultSalePrice());
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Product search(int id) {
        String sql = "SELECT * FROM product WHERE id=?";
        Product product = null;
            try (PreparedStatement stmt = ConnectionFactory.createStatement(sql)){
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()){
                    product = new Product(rs.getLong("id"), rs.getString("name"),
                            rs.getInt("quantity"), rs.getDouble("purchasePrice"),
                            rs.getDouble("purchaseSale"));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        return product;
    }
}
