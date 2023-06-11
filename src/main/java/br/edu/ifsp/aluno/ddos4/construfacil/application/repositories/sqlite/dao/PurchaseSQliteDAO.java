package br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.dao;

import br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.util.SQLiteConnectionFactory;
import br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.util.SQLiteDatabaseBuilder;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase.Purchase;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.supplier.Supplier;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao.PurchaseDAO;

import java.lang.constant.Constable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;
import java.util.Optional;

public class PurchaseSQliteDAO implements PurchaseDAO {
    @Override
    public void save(Purchase purchase) {
        String sql = "INSERT INTO Purchase (id_purchase, id_supplier, id_product, purchase_date)" +
                "VALUES (?, ?, ?, ?)";
        SQLiteConnectionFactory connectionFactory = new SQLiteConnectionFactory();

        //todo: ver como pegar o id de product
        try(PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)) {
            stmt.setLong(1, purchase.getId());
            stmt.setLong(2, purchase.getSupplier().getId());
            //stmt.setLong(3, purchase.get);
            stmt.setString(4, purchase.getIssueDate().toString());
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Purchase purchase) {
        String sql = "UPDATE Purchase SET purchase_date=? WHERE id_purchase=? AND " +
                "id_supplier=? AND id_product";
        SQLiteConnectionFactory connectionFactory = new SQLiteConnectionFactory();

        try(PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)) {
            stmt.setString(1, purchase.getIssueDate().toString());
            stmt.setLong(2, purchase.getId());
            stmt.setLong(3, purchase.getSupplier().getId());
            //stmt.setLong(4, purhcase);
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Map<Long, Purchase> findSomeByDate(LocalDate date) {
        return null;
    }

    @Override
    public Map<Long, Purchase> findSomeBySupplier(Supplier supplier) {
        String sql = "SELECT * FROM Purchase WHERE id_supplier=?";
        SQLiteConnectionFactory connectionFactory = new SQLiteConnectionFactory();

        try(PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)) {
            stmt.setLong(1, supplier.getId());
            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                Instant issueDateInstant = Instant.ofEpochMilli(rs.getDate("purchase_date").getTime());
//                LocalDateTime issueDate = LocalDateTime.ofInstant(issueDateInstant, ZoneId.systemDefault());
//                Long supllier_id = supplier.getId();
//
//                Purchase p = new Purchase(rs.getLong(issueDate, supllier_id);
//            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<Long, Purchase> findSomeBySupplierAndDate(Supplier supplier, LocalDate date) {
        return null;
    }

    @Override
    public long getTotalPrice() {
        return 0;
    }

    @Override
    public Optional<Purchase> findOneByKey(Long id) {
        return Optional.empty();
    }

    @Override
    public Map<Long, Purchase> findAll() {
        return null;
    }
}
