package br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.util;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseBuilder {
    public void buildDatabaseIfMissing(){
        if(!isDatabaseAvailable()){
            System.out.println("Database is missing. Building database: \n");
            buildTables();
        }
    }

    private boolean isDatabaseAvailable(){
        return Files.exists(Paths.get("database.db"));
    }

    private void buildTables() {
        try (Statement stmt = ConnectionFactory.createStatement()) {
            stmt.addBatch(createCashDeskTableSql());
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

            System.out.println("Database successfully created.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private String createCashDeskTableSql(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE CashDesk (\n");
        builder.append("id INTEGER NOT NULL PRIMARY KEY, \n");
        builder.append("initialBalance REAL NOT NULL, \n");
        builder.append("finalBalance REAL NOT NULL, \n");
        builder.append("cashIsOpen INTEGER NOT NULL, \n");
        builder.append("sales TEXT, \n");
        builder.append("purchases TEXT, \n");
        builder.append("refunds TEXT, \n");
        builder.append("saleRefunds TEXT\n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

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

        System.out.println(builder.toString());
        return builder.toString();
    }

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

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createSupplierTableSql(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Supplier (\n");
        builder.append("id INTEGER NOT NULL PRIMARY KEY, \n");
        builder.append("corporateName TEXT NOT NULL, \n");
        builder.append("cnpj TEXT NOT NULL, \n");
        builder.append("phoneNumber TEXT, \n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }
    
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

    private String createPurchaseItemTableSql(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE PurchaseItem (\n");
        builder.append("purchase INTEGER NOT NULL, \n");
        builder.append("product INTEGER NOT NULL, \n");
        builder.append("quantity INTEGER NOT NULL, \n");
        builder.append("actualPurchasePrice REAL NOT NULL, \n");
        builder.append("PRIMARY KEY (purchase, product), \n");
        builder.append("FOREIGN KEY(purchase) REFERENCES Purchase(id)\n");
        builder.append("FOREIGN KEY(product) REFERENCES Product(id)\n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

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

    private String createSaleItemTableSql(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE SaleItem (\n");
        builder.append("sale INTEGER NOT NULL, \n");
        builder.append("product INTEGER NOT NULL, \n");
        builder.append("quantity INTEGER NOT NULL, \n");
        builder.append("actualPrice REAL NOT NULL, \n");
        builder.append("PRIMARY KEY (sale, product), \n");
        builder.append("FOREIGN KEY(sale) REFERENCES Sale(id)\n");
        builder.append("FOREIGN KEY(product) REFERENCES Product(id)\n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

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
}
