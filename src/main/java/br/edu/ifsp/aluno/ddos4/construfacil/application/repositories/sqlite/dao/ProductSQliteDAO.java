//package br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.dao;
//
//import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product.Product;
//import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao.ProductDAO;
//import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.util.ConnectionFactory;
//
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.Map;
//import java.util.Optional;
//
//public class ProductSQliteDAO implements ProductDAO{
//    @Override
//    public void save(Product product) {
//        String sql = "INSERT INTO product (id, name, quantity, purchasePrice, purchaseSale)  " +
//                "VALUES (?, ?, ?, ?, ?)";
//        ConnectionFactory connectionFactory = new ConnectionFactory();
//        try(PreparedStatement stmt = connectionFactory.createPreparedStatement(sql)){
//            stmt.setLong(1, product.getId());
//            stmt.setString(2, product.getName());
//            stmt.setInt(3, product.getQuantity());
//            stmt.setDouble(4, product.getAvaragePurchasePrice());
//            stmt.setDouble(5, product.getDefaultSalePrice());
//            stmt.executeUpdate();
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void update(Product product) {
//        String sql = "UPDATE product SET name=?, quantity=?, purchasePrice=?, purchaseSale=?" +
//                "WHERE id=?";
//        ConnectionFactory connectionFactory = new ConnectionFactory();
//        try(PreparedStatement stmt = connectionFactory.createPreparedStatement(sql)){
//            stmt.setString(1, product.getName());
//            stmt.setInt(2, product.getQuantity());
//            stmt.setDouble(3, product.getAvaragePurchasePrice());
//            stmt.setDouble(4, product.getDefaultSalePrice());
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//    }
//    @Override
//    public Optional<Product> findOneByName(String name) {
//        return Optional.empty();
//    }
//
//    @Override
//    public Optional<Product> findOneByKey(Long id) {
//        return Optional.empty();
//    }
//
//    @Override
//    public Map<Long, Product> findAll() {
//        return null;
//    }
//}
