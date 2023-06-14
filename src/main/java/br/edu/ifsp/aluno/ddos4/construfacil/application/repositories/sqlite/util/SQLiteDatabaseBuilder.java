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

    public void buildTables() {
        SQLiteConnectionFactory connectionFactory = new SQLiteConnectionFactory();
        Connection connection = connectionFactory.getConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.addBatch(createCustomerTableSql());
            stmt.addBatch(createProductTableSql());
            stmt.addBatch(createSupplierTableSql());
            stmt.addBatch(createPurchaseTableSql());
            stmt.addBatch(createPurchaseRefundTableSql());
            stmt.addBatch(createCustomerBuysTableSql());
            stmt.addBatch(createSaleTableSql());
            stmt.addBatch(createSaleRefundTableSql());
            stmt.addBatch(createSupplierProvidesTableSql());

            stmt.executeBatch();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private String createCustomerTableSql(){
        return """
                CREATE TABLE Customer (
                    id_customer INTEGER PRIMARY KEY AUTOINCREMENT,\s
                    name VARCHAR(100) NOT NULL,\s
                    CPF VARCHAR(15) NOT NULL,\s
                    address VARCHAR(100),\s
                    phone_Number VARCHAR(20),\s
                    status VARCHAR(10) DEFAULT 'ACTIVE',\s
                    CONSTRAINT cpf_un UNIQUE (CPF)
                );
            """;
    }

    private String createProductTableSql(){
        return """
                CREATE TABLE Product (
                    id_product INTEGER PRIMARY KEY AUTOINCREMENT,\s
                    name VARCHAR(100) NOT NULL,\s
                    quantity NUMBER NOT NULL,\s
                    average_purchase_price NUMBER,\s
                    status VARCHAR(10) NOT NULL DEFAULT 'ACTIVE',\s
                    
                    CONSTRAINT name_un UNIQUE (name),\s
                    CONSTRAINT quantity_ck CHECK (quantity >= 0)\s
                    CONSTRAINT average_purchase_price_ck CHECK (average_purchase_price > 0)\s
                );\s
            """;
    }

    private String createSupplierTableSql(){
        return """
                CREATE TABLE Supplier (
                    id_supplier INTEGER PRIMARY KEY AUTOINCREMENT,\s
                    CNPJ VARCHAR(15) NOT NULL,\s
                    corporate_Name VARCHAR(100) NOT NULL,\s
                    Phone_Number VARCHAR(20),\s
                    status VARCHAR(10) NOT NULL DEFAULT 'ACTIVE',
                    
                    CONSTRAINT CNPJ_UN UNIQUE (CNPJ),
                    CONSTRAINT corporate_name_un UNIQUE (corporate_name)
                );\s
            """;
    }

    private String createPurchaseTableSql(){
        return """
                CREATE TABLE Purchase (
                    id_purchase INTEGER,\s
                    id_supplier INTEGER NOT NULL,\s
                    id_product INTEGER NOT NULL,\s
                    purchase_date TIMESTAMP NOT NULL,
                    
                    CONSTRAINT purchase_pk PRIMARY KEY (id_purchase),
                    CONSTRAINT purchase_supplier_supplies_fk FOREIGN KEY (id_supplier, id_product, purchase_date)
                    REFERENCES SUPPLIER_SUPPLIES(id_supplier, id_product, purchase_date)
                );\s
            """;
    }

    private String createPurchaseRefundTableSql(){
        return """
                CREATE TABLE Purchase_Refund (
                    id_purchase_refund INTEGER PRIMARY KEY AUTOINCREMENT,\s
                    id_purchase INTEGER,\s
                    
                    CONSTRAINT id_purchase_refund_fk FOREIGN KEY (id_purchase) REFERENCES purchase(id_purchase)
                );\s
            """;
    }

    private String createCustomerBuysTableSql() {
        return """
                CREATE TABLE customer_Buys (\s
                    id_product INTEGER,\s
                    id_customer INTEGER,
                    sale_date TIMESTAMP,
                    price NUMBER NOT NULL,
                    quantity NUMBER NOT NULL,
                    
                    CONSTRAINT customer_buys_pk PRIMARY KEY (id_product, id_customer, sale_date),
                    CONSTRAINT price_customer_ck CHECK (price > 0),
                    CONSTRAINT quantity_buys_ck CHECK (quantity > 0)
                );\s
            """;
    }

    private String createSaleTableSql(){
        return """
                CREATE TABLE Sale (
                    id_sale INTEGER,\s
                    id_customer INTEGER NOT NULL,\s
                    id_product INTEGER NOT NULL,\s
                    sale_date TIMESTAMP NOT NULL,CONSTRAINT sale_pk PRIMARY KEY (id_sale),
                
                    CONSTRAINT sale_customer_buys_fk FOREIGN KEY (id_product, id_customer, sale_date)
                        REFERENCES CUSTOMER_BUYS(id_product, id_customer, sale_date)
                );\s
            """;
    }

    private String createSaleRefundTableSql(){
        return """
                CREATE TABLE SaleRefund (
                    id_sale_refund INTEGER PRIMARY KEY AUTOINCREMENT,\s
                    id_sale        INTEGER,\s
                    
                    CONSTRAINT id_sale_refund_fk FOREIGN KEY (id_sale) REFERENCES sale(id_sale));\s
            """;
    }

    private String createSupplierProvidesTableSql() {
        return """
                CREATE TABLE supplier_provides(
                    id_supplier INTEGER,
                    id_product INTEGER,
                    purchase_date TIMESTAMP,
                    price NUMBER NOT NULL,
                    quantity NUMBER NOT NULL,
                
                    CONSTRAINT supplier_provides_pk PRIMARY KEY (id_supplier, id_product, purchase_date),
                    CONSTRAINT price_supplies_ck CHECK (price > 0),
                    CONSTRAINT quantity_supplies CHECK (quantity > 0)
                );
            """;
    }
}
