package br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.dao;

import br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.util.SQLiteConnectionFactory;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product.Product;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase.Purchase;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao.ProductDAO;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao.SupplierProvidesDAO;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.util.ConnectionFactory;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.purchase.PurchaseItemDTO;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.purchase.PurchaseItemKey;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.EntityNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

public class SupplierProvidesSQLiteDAO implements SupplierProvidesDAO {
    @Override
    public void save(PurchaseItemDTO purchaseItemDTO) {
        String sql = "INSERT INTO supplier_provides(id_supplier, id_product, purchase_date, price, quantity) VALUES " +
                        "(?, ?, ?, ?, ?)";
        ConnectionFactory connectionFactory = new SQLiteConnectionFactory();

        try(PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)) {
            stmt.setLong(1, purchaseItemDTO.key().supplierId());
            stmt.setLong(2, purchaseItemDTO.key().productId());
            stmt.setString(3, purchaseItemDTO.key().issueDate().toString());
            stmt.setLong(4, purchaseItemDTO.priceInCents());
            stmt.setLong(5, purchaseItemDTO.quantity());

            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<PurchaseItemDTO> findOneByKey(PurchaseItemKey key) {
        String sql = "SELECT id_supplier, purchase_date, id_product, price, quantity FROM supplier_provides WHERE " +
                        "id_supplier = ? and purchase_date = ? and id_product = productId";
        ConnectionFactory connectionFactory = new SQLiteConnectionFactory();

        try (PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)) {
            stmt.setLong(1, key.supplierId());
            stmt.setString(2, key.issueDate().toString());
            stmt.setLong(3, key.productId());

            ResultSet rs = stmt.executeQuery();

            if (rs.next())
                return Optional.of(resultSetToEntity(rs));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return  Optional.empty();
    }

    @Override
    public Map<PurchaseItemKey, PurchaseItemDTO> findSomeByPurchase(Purchase purchase) {
        String sql = "SELECT id_supplier, id_product, purchase_date, price, quantity FROM supplier_provides WHERE" +
                        "id_supplier = ? AND purchase_date = ?";
        ConnectionFactory connectionFactory = new SQLiteConnectionFactory();
        Map<PurchaseItemKey, PurchaseItemDTO> items = new HashMap<>();

        try (PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)) {
            stmt.setLong(1, purchase.getSupplier().getId());
            stmt.setString(2, purchase.getIssueDate().toString());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PurchaseItemDTO item = resultSetToEntity(rs);
                items.put(item.key(), item);
            }

            return items;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    @Override
    public Map<PurchaseItemKey, PurchaseItemDTO> findAll() {
        String sql = "SELECT id_supplier, purchase_date, id_product, price, quantity FROM supplier_provides";
        ConnectionFactory connectionFactory = new SQLiteConnectionFactory();
        Map<PurchaseItemKey, PurchaseItemDTO> items = new HashMap<>();

        try (PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PurchaseItemDTO item = resultSetToEntity(rs);
                items.put(item.key(), item);
            }

            return items;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    @Override
    public void update(PurchaseItemDTO purchaseItemDTO) {
        String sql = "UPDATE supplier_provides SET price = ?, quantity = ? WHERE id_supplier = ? AND " +
                        " purchase_date = ? AND id_product = ?";
        ConnectionFactory connectionFactory = new SQLiteConnectionFactory();

        try (PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)) {
            stmt.setLong(1, purchaseItemDTO.priceInCents());
            stmt.setLong(2, purchaseItemDTO.quantity());
            stmt.setLong(3, purchaseItemDTO.key().supplierId());
            stmt.setString(4, purchaseItemDTO.key().issueDate().toString());
            stmt.setLong(5, purchaseItemDTO.key().productId());

            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteByKey(PurchaseItemKey key) {
        String sql = "DELETE FROM supplier_provides WHERE id_supplier = ? AND purchase_date = ? AND id_product = ?";
        ConnectionFactory connectionFactory = new SQLiteConnectionFactory();

        try (PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)) {
            stmt.setLong(1, key.supplierId());
            stmt.setString(2, key.issueDate().toString());
            stmt.setLong(3, key.productId());

            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public long calculateAveragePurchasePriceByProductId(long productId) {
        String sql = "SELECT COALESCE(AVG(price), 0) AS average_purchase_price FROM supplier_provides WHERE " +
                        "id_product = ?";
        ConnectionFactory connectionFactory = new SQLiteConnectionFactory();

        try (PreparedStatement stmt = connectionFactory.getPreparedStatement(sql)) {
            stmt.setLong(1, productId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next())
                return rs.getLong("average_purchase_price");
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return 0;
    }

    public PurchaseItemDTO resultSetToEntity(ResultSet resultSet) throws SQLException {
        ProductDAO productDAO = new ProductSQLiteDAO();
        Product product = productDAO.findOneByKey(resultSet.getLong("id_product"))
                        .orElseThrow(() -> new EntityNotFoundException("Error while attempting to find the product!"));

        String purchaseDate = resultSet.getString("purchase_date").replace("T", " ");
        LocalDateTime issueDate = LocalDateTime.parse(purchaseDate);

        PurchaseItemKey key = new PurchaseItemKey(resultSet.getLong("id_supplier"), issueDate,
                                                    resultSet.getLong("product_id"));

        return new PurchaseItemDTO(key, product, resultSet.getLong("price"),
                                    resultSet.getLong("quantity"));
    }
}
