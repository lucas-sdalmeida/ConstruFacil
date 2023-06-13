//package br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.dao;
//
//import br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.util.SQLiteConnectionFactory;
//import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.supplier.Supplier;
//import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao.SupplierDAO;
//import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.util.ConnectionFactory;
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Optional;
//
//public class SupplierSQliteDAO implements SupplierDAO {
//
//    @Override
//    public void save(Supplier supplier) {
//        String sql = "INSERT INTO Supplier (id_supplier, CNPJ, corporate_Name, Phone_Number)" +
//                "VALUES (?, ?, ?, ?)";
//        SQLiteConnectionFactory connectionFactory = new SQLiteConnectionFactory();
//
//        try(PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)) {
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
//        String sql = "UPDATE Supplier SET CNPJ=?, corporate_Name=?, Phone_Number=? WHERE id_supplier=?";
//        SQLiteConnectionFactory connectionFactory = new SQLiteConnectionFactory();
//
//        try(PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)) {
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
//        String sql = "SELECT id_supplier, CNPJ, corporate_Name, Phone_Number FROM Supplier WHERE id_supplier=?";
//        SQLiteConnectionFactory connectionFactory = new SQLiteConnectionFactory();
//
//        try(PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)) {
//            stmt.setLong(1, id);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()){
//                Supplier s = new Supplier(rs.getLong("id_supplier"), rs.getString("CNPJ"),
//                        rs.getString("corporate_Name"), rs.getString("Phone_Number"));
//                return Optional.of(s);
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//        return Optional.empty();
//    }
//
//    @Override
//    public Optional<Supplier> findOneByCNPJ(String cnpj) {
//        String sql = "SELECT id_supplier, CNPJ, corporate_Name, Phone_Number FROM Supplier WHERE CNPJ=?";
//        SQLiteConnectionFactory connectionFactory = new SQLiteConnectionFactory();
//
//        try(PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)) {
//            stmt.setString(1, cnpj);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()){
//                Supplier s = new Supplier(rs.getLong("id_supplier"), rs.getString("CNPJ"),
//                        rs.getString("corporate_Name"), rs.getString("Phone_Number"));
//                return Optional.of(s);
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//
//        return Optional.empty();
//    }
//
//    @Override
//    public Map<Long, Supplier> findAll() {
//        String sql = "SELECT * FROM Supplier";
//        SQLiteConnectionFactory connectionFactory = new SQLiteConnectionFactory();
//        Map<Long, Supplier> suppliers = new HashMap<>();
//
//        try(PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)){
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()){
//                Supplier s = new Supplier(rs.getLong("id_supplier"), rs.getString("CNPJ"),
//                        rs.getString("corporate_Name"), rs.getString("Phone_Number"));
//                suppliers.put(s.getId(), s);
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//        return suppliers;
//    }
//}
