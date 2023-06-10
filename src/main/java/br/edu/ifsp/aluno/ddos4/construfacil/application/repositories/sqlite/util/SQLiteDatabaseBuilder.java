package br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.util;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.util.DataBaseBuilder;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public final class SQLiteDatabaseBuilder implements DataBaseBuilder {
    @Override
    public void buildDatabaseIfMissing(){
        if(!isDatabaseAvailable()){
            buildTables();
        }
    }

    @Override
    public boolean isDatabaseAvailable(){
        return Files.exists(Paths.get("database.db"));
    }

    private void buildTables() {
        SQLiteConnectionFactory connectionFactory = new SQLiteConnectionFactory();

        try (Connection connection = connectionFactory.getConnection();
                Statement stmt = connection.createStatement()) {
            stmt.addBatch(createCustomerTableSql());
            stmt.addBatch(createProductTableSql());
            stmt.addBatch(createSupplierTableSql());
            stmt.addBatch(createPurchaseTableSql());
            stmt.addBatch(createPurchaseItemTableSql());
            stmt.addBatch(createPurchaseRefundTableSql());
            stmt.addBatch(createSaleTableSql());
            stmt.addBatch(createSaleItemTableSql());
            stmt.addBatch(createSaleRefundTableSql());

            stmt.executeBatch();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // todo: substituir o sql da tabela CUSTOMER pelo definitivo
    private String createCustomerTableSql(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Customer (\n");
        builder.append("id INTEGER NOT NULL PRIMARY KEY, \n");
        builder.append("name TEXT NOT NULL, \n");
        builder.append("cpf TEXT NOT NULL, \n");
        builder.append("address TEXT, \n");
        builder.append("phoneNumber TEXT, \n");
        builder.append("active INTEGER NOT NULL \n");
        builder.append("); \n");

        return builder.toString();
    }

    // todo: substituir o sql da tabela PRODUCT pela definitiva
    private String createProductTableSql(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Product (\n");
        builder.append("id INTEGER NOT NULL PRIMARY KEY, \n");
        builder.append("name TEXT NOT NULL, \n");
        builder.append("quantity INTEGER NOT NULL, \n");
        builder.append("defaultPurchasePrice REAL NOT NULL, \n");
        builder.append("defaultSalePrice REAL NOT NULL, \n");
        builder.append("active INTEGER NOT NULL \n");
        builder.append("); \n");

        return builder.toString();
    }

    // todo: substituir a implementação da tabela Supplier
    private String createSupplierTableSql(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Supplier (\n");
        builder.append("id INTEGER NOT NULL PRIMARY KEY, \n");
        builder.append("corporateName TEXT NOT NULL, \n");
        builder.append("cnpj TEXT NOT NULL, \n");
        builder.append("phoneNumber TEXT, \n");
        builder.append("); \n");

        return builder.toString();
    }

    // todo: substituir a implementação da tabela Purchase
    private String createPurchaseTableSql(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Purchase (\n");
        builder.append("id INTEGER NOT NULL PRIMARY KEY, \n");
        builder.append("date TEXT NOT NULL, \n");
        builder.append("supplier INTEGER NOT NULL, \n");
        builder.append("FOREIGN KEY(supplier) REFERENCES Supplier(id)\n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    // todo: substituir a implementação da tabela Purchase_Refund
    private String createPurchaseRefundTableSql(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE PurchaseRefund (\n");
        builder.append("id INTEGER NOT NULL PRIMARY KEY, \n");
        builder.append("purchase INTEGER NOT NULL, \n");
        builder.append("FOREIGN KEY(purchase) REFERENCES Purchase(id)\n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    // todo: substituir a implementação da tabela Sale
    private String createSaleTableSql(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Sale (\n");
        builder.append("id INTEGER NOT NULL PRIMARY KEY, \n");
        builder.append("date TEXT NOT NULL, \n");
        builder.append("customer INTEGER NOT NULL, \n");
        builder.append("FOREIGN KEY(customer) REFERENCES Customer(id)\n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    // todo: substituir a implementação da tabela Sale_Refund
    private String createSaleRefundTableSql(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE SaleRefund (\n");
        builder.append("id INTEGER NOT NULL PRIMARY KEY, \n");
        builder.append("sale INTEGER NOT NULL, \n");
        builder.append("FOREIGN KEY(sale) REFERENCES Sale(id)\n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    // todo: a implementação da tabela Customer_Buys
    private String createCustomerBuysTableSql() {}

    // todo: a implementação da tabela Supplier_Provides
    private String createSupplierProvidesTableSql() {}
}
