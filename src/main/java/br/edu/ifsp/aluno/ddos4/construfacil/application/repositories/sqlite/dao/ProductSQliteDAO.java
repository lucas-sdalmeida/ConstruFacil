package br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.dao;

import br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.util.SQLiteConnectionFactory;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product.Product;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.util.RegistrationStatus;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao.ProductDAO;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.util.ConnectionFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ProductSQliteDAO implements ProductDAO{
    @Override
    public void save(Product product) {
        String sql = "INSERT INTO product (id_product, name, quantity, average_purchase_price) " +
                            "VALUES (?, ?, ?, 0)";
        SQLiteConnectionFactory connectionFactory = new SQLiteConnectionFactory();

        try(PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)) {
            stmt.setLong(1, product.getId());
            stmt.setString(2, product.getName());
            stmt.setLong(3, product.getStockQuantity());

            stmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Product product) {
        String sql = "UPDATE product SET name=?, quantity=?, average_purchase_price=?, status=? WHERE id_product=?";
        SQLiteConnectionFactory connectionFactory = new SQLiteConnectionFactory();

        try(PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)){
            stmt.setString(1, product.getName());
            stmt.setLong(2, product.getStockQuantity());
            stmt.setLong(3, product.getAveragePurchasePriceInCents());
            stmt.setString(4, product.getStatus().toString());
            stmt.setLong(5, product.getId());

            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Override
    public Optional<Product> findOneByName(String name) {
        String sql = "SELECT id_product, name, quantity, average_purchase_price, status FROM product WHERE name=?";
        SQLiteConnectionFactory connectionFactory = new SQLiteConnectionFactory();

        try(PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                Product p = new Product(rs.getLong("id_product"),
                                        rs.getString("name"),
                                        rs.getLong("quantity"),
                                        rs.getLong("average_purchase_price"),
                                        RegistrationStatus.fromString(rs.getString("status"))
                                );

                return Optional.of(p);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<Product> findOneByKey(Long id) {
        String sql = "SELECT id_product, name, quantity, average_purchase_price, status FROM " +
                            "product WHERE id_product=?";
        SQLiteConnectionFactory connectionFactory = new SQLiteConnectionFactory();

        try(PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                Product p = new Product(rs.getLong("id_product"),
                                        rs.getString("name"),
                                        rs.getLong("quantity"),
                                        rs.getLong("average_purchase_price"),
                                        RegistrationStatus.fromString(rs.getString("status"))
                                );

                return Optional.of(p);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Map<Long, Product> findAll() {
        String sql = "SELECT id_product, name, quantity, average_purchase_price, status FROM product";
        SQLiteConnectionFactory connectionFactory = new SQLiteConnectionFactory();
        Map<Long, Product> products = new HashMap<>();

        try(PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

           while (rs.next()){
                Product p = new Product(rs.getLong("id_product"),
                                        rs.getString("name"),
                                        rs.getLong("quantity"),
                                        rs.getLong("average_purchase_price"),
                                        RegistrationStatus.fromString(rs.getString("status"))
                                );

                products.put(p.getId(), p);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public void deleteByKey(Long key) {
        String sql = "DELETE FROM product WHERE id_product = ?";
        ConnectionFactory connectionFactory = new SQLiteConnectionFactory();

        try (PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)) {
            stmt.setLong(1, key);

            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public long getAveragePurchasePriceInCentsByProduct(Product product) {
        String sql = "SELECT average_purchase_price FROM product WHERE id_product = ?";
        SQLiteConnectionFactory connectionFactory = new SQLiteConnectionFactory();

        try(PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)){
            stmt.setLong(1, product.getId());
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
                return rs.getLong("average_purchase_price");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0L;
    }

    @Override
    public void inactivate(Product product) {
        String sql = "UPDATE product SET status = inactive WHERE id_product = ?";
        ConnectionFactory connectionFactory = new SQLiteConnectionFactory();

        try (PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)) {
            stmt.setLong(1, product.getId());

            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void activate(Product product) {
        String sql = "UPDATE product SET status = active WHERE id_product = ?";
        ConnectionFactory connectionFactory = new SQLiteConnectionFactory();

        try (PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)) {
            stmt.setLong(1, product.getId());

            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
