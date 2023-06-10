//package br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.dao;
//
//import br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.util.SQLiteConnectionFactory;
//import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.supplier.Supplier;
//import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao.SupplierDAO;
//import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.util.ConnectionFactory;
//
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.Map;
//import java.util.Optional;
//
//public class SupplierSQliteDAO implements SupplierDAO {
//
//    @Override
//    public void save(Supplier supplier) {
//        String sql = "INSERT INTO supplier (id, cnpj, corporateName, phoneNumber) " +
//                "VALUES (?, ?, ?, ?)";
//        ConnectionFactory connectionFactory = new ConnectionFactory();
//        try (PreparedStatement stmt = connectionFactory.createPreparedStatement(sql)) {
//            stmt.setLong(1, supplier.getId());
//            stmt.setString(2, supplier.getCnpj());
//            stmt.setString(3, supplier.getCorporateName());
//            stmt.setString(4, supplier.getPhoneNumber());
//            stmt.executeUpdate();
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void update(Supplier supplier) {
//        String sql = "UPDATE supplier SET cnpj=?, corporateName=?, phoneNumber=? WHERE id=?";
//        ConnectionFactory connectionFactory = new ConnectionFactory();
//        try (PreparedStatement stmt = connectionFactory.createPreparedStatement(sql)){
//            stmt.setString(1, supplier.getCnpj());
//            stmt.setString(2, supplier.getCorporateName());
//            stmt.setString(3, supplier.getPhoneNumber());
//            stmt.setLong(4, supplier.getId());
//            stmt.executeUpdate();
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public Optional<Supplier> findOneByKey(Long id) {
//        return Optional.empty();
//    }
//
//    @Override
//    public Optional<Supplier> findOneByCNPJ(String corporateName) {
//        return Optional.empty();
//    }
//
//    @Override
//    public Map<Long, Supplier> findAll() {
//        return null;
//    }
//}
