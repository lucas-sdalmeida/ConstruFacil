package br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.dao;

import br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.util.SQLiteConnectionFactory;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product.Product;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao.ProductDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ProductSQliteDAO implements ProductDAO{
    @Override
    public void save(Product product) {
        String sql = "INSERT INTO Product (id_product, name, quantity, average_Purchase_Price) " +
                "VALUES (?, ?, ?, ?)";
        SQLiteConnectionFactory connectionFactory = new SQLiteConnectionFactory();

        try(PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)) {
            stmt.setLong(1, product.getId());
            stmt.setString(2, product.getName());
            stmt.setInt(3, product.getStockQuantity());
            stmt.setLong(4, product.getAveragePurchasePriceInCents());
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Product product) {
        String sql = "UPDATE Product SET name=?, quantity=?, average_Purchase_Price=? WHERE id_product=?";
        SQLiteConnectionFactory connectionFactory = new SQLiteConnectionFactory();

        try(PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)){
            stmt.setString(1, product.getName());
            stmt.setInt(2, product.getStockQuantity());
            stmt.setLong(3, product.getAveragePurchasePriceInCents());
            stmt.setLong(4, product.getId());
            stmt.executeUpdate();
            try {
                stmt.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Override
    public Optional<Product> findOneByName(String name) {
        String sql = "SELECT id_product, name, quantity, average_Purchase_Price FROM Product WHERE name=?";
        SQLiteConnectionFactory connectionFactory = new SQLiteConnectionFactory();
        Product product;

        try(PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                Product p = new Product(rs.getLong("id_product"), rs.getString("name"),
                        rs.getInt("quantity"), rs.getLong("average_Purchase_Price"));
                return Optional.of(p);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<Product> findOneByKey(Long id) {
        String sql = "SELECT id_product, name, quantity, average_Purchase_Price FROM Product WHERE id_product=?";
        SQLiteConnectionFactory connectionFactory = new SQLiteConnectionFactory();

        try(PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                Product p = new Product(rs.getLong("id_product"), rs.getString("name"),
                        rs.getInt("quantity"), rs.getLong("average_Purchase_Price"));
                return Optional.of(p);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Map<Long, Product> findAll() {
        String sql = "SELECT * FROM Product";
        SQLiteConnectionFactory connectionFactory = new SQLiteConnectionFactory();
        Map<Long, Product> products = new HashMap<>();

        try(PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
           while (rs.next()){
                Product p = new Product(rs.getLong("id_product"), rs.getString("name"),
                        rs.getInt("quantity"), rs.getLong("average_Purchase_Price"));
                products.put(p.getId(), p);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public long getAveragePurchasePriceInCentsByProduct(Product product) {
        String sql = "SELECT id_product, name, quantity, average_Purchase_Price FROM Product WHERE average_Purchase_Price=?";
        SQLiteConnectionFactory connectionFactory = new SQLiteConnectionFactory();

        try(PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)){
            stmt.setLong(1, product.getAveragePurchasePriceInCents());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                Product p = new Product(rs.getLong("id_product"), rs.getString("name"),
                        rs.getInt("quantity"), rs.getLong("average_Purchase_Price"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return 0;
    }
}
