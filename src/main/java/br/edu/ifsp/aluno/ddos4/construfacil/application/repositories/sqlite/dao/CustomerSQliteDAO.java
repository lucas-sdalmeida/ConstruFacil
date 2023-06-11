package br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.dao;

import br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.util.SQLiteConnectionFactory;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.customer.Customer;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao.CustomerDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CustomerSQliteDAO implements CustomerDAO{
    @Override
    public void save(Customer customer) {
        String sql = "INSERT INTO customer (id_customer, name, CPF, address, phone_Number, status) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        SQLiteConnectionFactory connectionFactory = new SQLiteConnectionFactory();

        try(PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)){
            stmt.setLong(1, customer.getId());
            stmt.setString(2, customer.getName());
            stmt.setString(3, customer.getCpf());
            stmt.setString(4, customer.getAddress());
            stmt.setString(5, customer.getPhoneNumber());
            stmt.executeUpdate();
            try {
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Customer customer) {
        String sql = "UPDATE customer SET name=?, CPF=?, address=?, phone_Number=? WHERE id_customer=?";

        SQLiteConnectionFactory connection = new SQLiteConnectionFactory();

        try(PreparedStatement stmt = connection.getPreparedStatement(sql)) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getCpf());
            stmt.setString(3, customer.getAddress());
            stmt.setString(4, customer.getPhoneNumber());
            stmt.setLong(5, customer.getId());
            stmt.executeUpdate();
            try {
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Customer> findOneByKey(Long id) {
        String sql = "SELECT name, CPF, address, phone_Number FROM customer WHERE id_customer=?";
        SQLiteConnectionFactory connectionFactory = new SQLiteConnectionFactory();

        try(PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)){
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Customer c = new Customer(rs.getString("name"), rs.getString("CPF"),
                        rs.getString("address"), rs.getString("phone_Number"));
                return Optional.of(c);
            }
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
                Customer c = new Customer(rs.getLong("id_customer"), rs.getString("name"),
                        rs.getString("CPF"), rs.getString("address"),
                        rs.getString("phone_Number"));
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
            if (rs.next()) {
                Customer c = new Customer(rs.getLong("id_customer") , rs.getString("name"), rs.getString("CPF"),
                        rs.getString("address"), rs.getString("phone_Number"));
                return Optional.of(c);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
