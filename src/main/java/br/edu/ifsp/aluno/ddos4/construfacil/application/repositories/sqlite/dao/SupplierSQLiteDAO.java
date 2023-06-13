package br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.dao;

import br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.util.SQLiteConnectionFactory;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.supplier.Supplier;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.util.RegistrationStatus;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao.SupplierDAO;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.util.ConnectionFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SupplierSQLiteDAO implements SupplierDAO {
    @Override
    public void save(Supplier supplier) {
        String sql = "INSERT INTO Supplier (CNPJ, corporate_Name, Phone_Number) VALUES (?, ?, ?)";
        SQLiteConnectionFactory connectionFactory = new SQLiteConnectionFactory();

        try(PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)) {
            stmt.setString(1, supplier.getCnpj());
            stmt.setString(2, supplier.getCorporateName());
            stmt.setString(3, supplier.getPhoneNumber());
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Supplier supplier) {
        String sql = "UPDATE Supplier SET corporate_Name=?, Phone_Number=?, status=? WHERE id_supplier=?";
        SQLiteConnectionFactory connectionFactory = new SQLiteConnectionFactory();

        try(PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)) {
            stmt.setString(1, supplier.getCorporateName());
            stmt.setString(2, supplier.getPhoneNumber());
            stmt.setString(3, supplier.getStatus().toString());
            stmt.setLong(4, supplier.getId());
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Supplier> findOneByKey(Long id) {
        String sql = "SELECT id_supplier, cnpj, corporate_name, phone_number, status FROM Supplier WHERE id_supplier=?";
        SQLiteConnectionFactory connectionFactory = new SQLiteConnectionFactory();

        try(PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
                return Optional.of(resultSetToEntity(rs));
        }catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Supplier> findOneByCNPJ(String cnpj) {
        String sql = "SELECT id_supplier, cnpj, corporate_name, phone_number, status FROM Supplier WHERE cnpj=?";
        SQLiteConnectionFactory connectionFactory = new SQLiteConnectionFactory();

        try(PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)) {
            stmt.setString(1, cnpj);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
                return Optional.of(resultSetToEntity(rs));
        }catch (SQLException e){
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Map<Long, Supplier> findAll() {
        String sql = "SELECT id_supplier,cnpj, corporate_name, phone_number, status FROM Supplier";
        SQLiteConnectionFactory connectionFactory = new SQLiteConnectionFactory();
        Map<Long, Supplier> suppliers = new HashMap<>();

        try(PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Supplier s = resultSetToEntity(rs);
                suppliers.put(s.getId(), s);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return suppliers;
    }

    @Override
    public void deleteByKey(Long key) {
        String sql = "DELETE FROM supplier WHERE id_supplier = key";
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
    public void inactivate(Supplier supplier) {
        String sql = "UPDATE supplier SET status = 'INACTIVE' WHERE id_supplier = ?";
        ConnectionFactory connectionFactory = new SQLiteConnectionFactory();

        try (PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)) {
            stmt.setLong(1, supplier.getId());
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void activate(Supplier supplier) {
        String sql = "UPDATE supplier SET status = 'ACTIVE' WHERE id_supplier = ?";
        ConnectionFactory connectionFactory = new SQLiteConnectionFactory();

        try (PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)) {
            stmt.setLong(1, supplier.getId());
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Supplier resultSetToEntity(ResultSet resultSet) throws SQLException {
        return new Supplier(resultSet.getLong("id_supplier"),
                            resultSet.getString("cnpj"),
                            resultSet.getString("corporate_name"),
                            resultSet.getString("phone_number"),
                            RegistrationStatus.fromString(resultSet.getString("status"))
                    );
    }
}
