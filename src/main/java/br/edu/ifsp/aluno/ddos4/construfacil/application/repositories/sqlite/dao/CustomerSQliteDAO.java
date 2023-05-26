package br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.dao;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.customer.Customer;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao.CustomerDAO;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.util.ConnectionFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

public class CustomerSQliteDAO implements CustomerDAO{
    /* */
    @Override
    public void save(Customer customer) {
        String sql = "INSERT INTO customer (id, name, cpf, address, phoneNumber) " +
                "VALUES (?, ?, ?, ?, ?)";
        ConnectionFactory connectionFactory = new ConnectionFactory();

        try(PreparedStatement stmt = connectionFactory.createPreparedStatement(sql)){
            stmt.setLong(1, customer.getId());
            stmt.setString(2, customer.getName());
            stmt.setString(3, customer.getCpf());
            stmt.setString(4, customer.getAddress());
            stmt.setString(5, customer.getPhoneNumber());
            stmt.executeUpdate();
            try {
                connectionFactory.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Customer customer) {
        String sql = "UPDATE customer SET name=?, cpf=?, address=?, phoneNumber=? WHERE id=?";
        ConnectionFactory connectionFactory = new ConnectionFactory();
        try(PreparedStatement stmt = connectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getCpf());
            stmt.setString(3, customer.getAddress());
            stmt.setString(4, customer.getPhoneNumber());
            stmt.setLong(5, customer.getId());
            stmt.executeUpdate();
            try {
                connectionFactory.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Customer> findOneByKey(Long id) {
        return Optional.empty();
    }

    @Override
    public Map<Long, Customer> findAll() {
        return null;
    }

    @Override
    public Optional<Customer> findOneByCPF(String cpf) {
        return Optional.empty();
    }
}
