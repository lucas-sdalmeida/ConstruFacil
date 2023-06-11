package br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.dao;

import br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.util.SQLiteConnectionFactory;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product.Product;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.supplier.Supplier;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao.SuplliesProvidesDAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

public class SuppliersProvidesSQliteDAO implements SuplliesProvidesDAO {

    @Override
    public void save(Supplier supplier, Product product) {
        String sql = "INSERT INTO supplier_provides (id_supplier, id_product, purchase_date, price, quantity)";
        SQLiteConnectionFactory connectionFactory = new SQLiteConnectionFactory();
        try(PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)) {
            stmt.setLong(1, supplier.getId());
            stmt.setLong(2, product.getId());
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void save(Supplier type) {

    }

    @Override
    public void update(Supplier type) {

    }

    @Override
    public Optional<Supplier> findOneByKey(Long id) {
        return Optional.empty();
    }

    @Override
    public Map<Long, Supplier> findAll() {
        return null;
    }
}
