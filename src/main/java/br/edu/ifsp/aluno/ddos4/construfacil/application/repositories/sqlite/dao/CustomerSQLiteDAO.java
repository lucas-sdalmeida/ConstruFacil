package br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.dao;

import br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.util.SQLiteConnectionFactory;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.customer.Customer;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.util.RegistrationStatus;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao.CustomerDAO;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.util.ConnectionFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CustomerSQLiteDAO implements CustomerDAO{
    @Override
    public void save(Customer customer) {
        String sql = "INSERT INTO customer (name, CPF, address, phone_Number) " +
                        "VALUES (?, ?, ?, ?)";
        SQLiteConnectionFactory connectionFactory = new SQLiteConnectionFactory();

        try(PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)){
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getCpf());
            stmt.setString(3, customer.getAddress());
            stmt.setString(4, customer.getPhoneNumber());
            stmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Customer customer) {
        String sql = "UPDATE customer SET name = ?, address = ?, phone_Number = ?, status = ? WHERE id_customer = ?";

        SQLiteConnectionFactory connection = new SQLiteConnectionFactory();

        try(PreparedStatement stmt = connection.getPreparedStatement(sql)) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getAddress());
            stmt.setString(3, customer.getPhoneNumber());
            stmt.setString(4, customer.getStatus().toString());
            stmt.setLong(5, customer.getId());
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Customer> findOneByKey(Long id) {
        String sql = "SELECT id_customer, name, CPF, address, phone_Number, status FROM customer WHERE id_customer=?";
        SQLiteConnectionFactory connectionFactory = new SQLiteConnectionFactory();

        try(PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)){
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
    public Map<Long, Customer> findAll() {
        String sql = "SELECT id_customer, name, CPF, address, phone_Number FROM customer";
        SQLiteConnectionFactory connectionFactory = new SQLiteConnectionFactory();
        Map<Long, Customer> customers = new HashMap<>();

        try(PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Customer c = resultSetToEntity(rs);
                customers.put(c.getId(), c);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public Optional<Customer> findOneByCPF(String cpf) {
        String sql = "SELECT id_customer, name, CPF, address, phone_Number FROM customer WHERE CPF=?";
        SQLiteConnectionFactory connectionFactory = new SQLiteConnectionFactory();

        try(PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
                return Optional.of(resultSetToEntity(rs));
        }catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void deleteByKey(Long key) {
        String sql = "DELETE FROM customer WHERE id_customer = ?";
        ConnectionFactory connectionFactory= new SQLiteConnectionFactory();

        try (PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)) {
            stmt.setLong(1, key);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void inactivate(Customer customer) {
        String sql = "UPDATE customer SET status = 'INACTIVE' WHERE id_customer = ?";
        ConnectionFactory connectionFactory = new SQLiteConnectionFactory();

        try (PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)) {
            stmt.setLong(1, customer.getId());
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void activate(Customer customer) {
        String sql = "UPDATE customer SET status = 'ACTIVE' WHERE id_customer = ?";
        ConnectionFactory connectionFactory = new SQLiteConnectionFactory();

        try (PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)) {
            stmt.setLong(1, customer.getId());
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer resultSetToEntity(ResultSet resultSet) throws SQLException {
        return new Customer(resultSet.getLong("id_customer"),
                resultSet.getString("name"),
                resultSet.getString("CPF"),
                resultSet.getString("address"),
                resultSet.getString("phone_Number"),
                RegistrationStatus.fromString(resultSet.getString("status"))
        );
    }
}
