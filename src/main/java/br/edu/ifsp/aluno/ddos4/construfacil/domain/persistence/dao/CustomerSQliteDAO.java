package br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.customer.Customer;
<<<<<<< Updated upstream:src/main/java/br/edu/ifsp/aluno/ddos4/construfacil/domain/persistence/dao/CustomerSQliteDAO.java
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.util.ConnectionFactory;
=======
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao.CustomerDAO;
>>>>>>> Stashed changes:src/main/java/br/edu/ifsp/aluno/ddos4/construfacil/application/repositories/sqlite/dao/CustomerSQliteDAO.java

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

<<<<<<< Updated upstream:src/main/java/br/edu/ifsp/aluno/ddos4/construfacil/domain/persistence/dao/CustomerSQliteDAO.java

public class CustomerSQliteDAO implements CustomerDAO{
=======
public class CustomerSQliteDAO implements CustomerDAO {
>>>>>>> Stashed changes:src/main/java/br/edu/ifsp/aluno/ddos4/construfacil/application/repositories/sqlite/dao/CustomerSQliteDAO.java

    @Override
    public void save(Customer customer) {
        String sql = "INSERT INTO customer (id, name, cpf, address, phoneNumber) " +
                "VALUES (?, ?, ?, ?, ?)";
        try(PreparedStatement stmt = ConnectionFactory.getPreparedStatement(sql)) {
            stmt.setLong(1, customer.getId());
            stmt.setString(2, customer.getName());
            stmt.setString(3, customer.getCpf());
            stmt.setString(4, customer.getAddress());
            stmt.setString(5, customer.getPhoneNumber());
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Customer customer) {
        String sql = "UPDATE customer SET name=?, cpf=?, address=?, phoneNumber=? WHERE id=?";
        try(PreparedStatement stmt = ConnectionFactory.getPreparedStatement(sql)) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getCpf());
            stmt.setString(3, customer.getAddress());
            stmt.setString(4, customer.getPhoneNumber());
            stmt.setLong(5, customer.getId());
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Customer search(int id) {
        String sql = "SELECT * FROM customer WHERE id=?";
        Customer customer;
        try(PreparedStatement stmt = ConnectionFactory.getPreparedStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                customer = new Customer(rs.getLong("id"), rs.getString("name"),
                        rs.getString("cpf"), rs.getString("address"),
                        rs.getString("phoneNumber"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }
}
