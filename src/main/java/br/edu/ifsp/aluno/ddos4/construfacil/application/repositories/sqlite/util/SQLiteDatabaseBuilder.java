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

    //todo: conferir se o default está sendo usado corretamente
    private String createCustomerTableSql(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Customer (\n");
        builder.append("id_customer NUMBER NOT NULL, \n");
        builder.append("name VARCHAR(100) NOT NULL, \n");
        builder.append("CPF VARCHAR(15) NOT NULL, \n");
        builder.append("address VARCHAR(100), \n");
        builder.append("phone_Number VARCHAR(20), \n");
        builder.append("status VARCHAR(10) DEFUALT 'ACTIVE', \n");
        builder.append("CONSTRAINT customer_pk PRIMARY KEY (id_customer),");
        builder.append("CONSTRAINT cpf_un UNIQUE (CPF)");
        builder.append(");\n");

        return builder.toString();
    }

    //todo: conferir o o default está correto
    private String createProductTableSql(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Product (\n");
        builder.append("id_product NUMBER NOT NULL, \n");
        builder.append("name VARCHAR(100) NOT NULL, \n");
        builder.append("quantity NUMBER NOT NULL, \n");
        builder.append("average_Purchase_Price NUMBER, \n");
        builder.append("status VARCHAR(10) NOT NULL DEFAULT 'ACTIVE', \n");
        builder.append("CONSTRAINT product_pk PRIMARY KEY (id_product), \n");
        builder.append("CONSTRAINT name_un  UNIQUE (name), \n");
        builder.append("CONSTRAINT quantity_ck CHECK (quantity > 0), \n");
        builder.append("CONSTRAINT average_purchase_price_ck UNIQUE (average_purchase_price > 0) \n");
        builder.append("); \n");

        return builder.toString();
    }

    private String createSupplierTableSql(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Supplier (\n");
        builder.append("id_supplier NUMBER, \n");
        builder.append("CNPJ VARCHAR(15) NOT NULL, \n");
        builder.append("corporate_Name VARCHAR(100) NOT NULL, \n");
        builder.append("Phone_Number VARCHAR(20), \n");
        builder.append("CONSTRAINT supplier_pk PRIMARY KEY (id_supplier),");
        builder.append("CONSTRAINT CNPJ_UN UNIQUE (CNPJ),");
        builder.append("CONSTRAINT corporate_name_un UNIQUE (corporate_name)");
        builder.append("); \n");

        return builder.toString();
    }

    private String createPurchaseTableSql(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Purchase (\n");
        builder.append("id_purchase NUMBER, \n");
        builder.append("id_supplier NUMBER NOT NULL, \n");
        builder.append("id_product NUMBER NOT NULL, \n");
        builder.append("purchase_date TIMESTAMP NOT NULL,\n");
        builder.append("CONSTRAINT purchase_pk PRIMARY KEY purchase(id_purchase),");
        builder.append("CONSTRAINT purchase_supplier_supplies_fk FOREIGN KEY (id_supplier, id_product, purchase_date)");
        builder.append("REFERENCES SUPPLIER_SUPPLIES(id_supplier, id_product, purchase_date)");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createPurchaseRefundTableSql(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Purchase_Refund (\n");
        builder.append("id_purchase_refund NUMBER, \n");
        builder.append("id_purchase NUMBER, \n");
        builder.append("CONSTRAINT purchase_refund_pk PRIMARY KEY (id_purchase_refund),\n");
        builder.append("CONSTRAINT id_purchase_refund_fk FOREIGN KEY (id_purchase) REFERENCES purchase(id_purchase)");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createSaleTableSql(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Sale (\n");
        builder.append("id_sale NUMBER, \n");
        builder.append("id_customer NUMBER NOT NULL, \n");
        builder.append("id_product NUMBER NOT NULL, \n");
        builder.append("sale_date TIMESTAMP NOT NULL,");
        builder.append("CONSTRAINT sale_pk PRIMARY KEY sale(id_sale),\n");
        builder.append("CONSTRAINT sale_customer_buys_fk FOREIGN KEY (id_pruduct, id_customer, sale_date)");
        builder.append("REFERENCES CUSTOMER_BUYS(id_pruduct, id_customer, sale_date)");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createSaleRefundTableSql(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE SaleRefund (\n");
        builder.append("id_sale_refund NUMBER, \n");
        builder.append("id_sale        NUMBER, \n");
        builder.append("CONSTRAINT sale_refund_pk PRIMARY KEY (id_sale_refund),\n");
        builder.append("CONSTRAINT id_sale_refund_fk FOREIGN KEY (id_sale) REFERENCES sale(id_sale)");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createCustomerBuysTableSql() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE customer_Buys ( \n");
        builder.append("id_product NUMBER, \n");
        builder.append("id_customer NUMBER,\n");
        builder.append("sale_date TIMESTAMP,\n");
        builder.append("price NUMBER NOT NULL,\n");
        builder.append("quantity NUMBER NOT NULL,\n");
        builder.append("CONSTRAINT customer_buys_pk PRIMARY KEY (id_product, id_customer, sale_date),\n");
        builder.append("CONSTRAINT price_customer_ck CHECK (price > 0),\n");
        builder.append("CONSTRAINT quantity_buyies_ck CHECK (quantity > 0)\n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createSupplierProvidesTableSql() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE supplier_provides(\n");
        builder.append("id_supplier NUMBER,\n");
        builder.append("id_product NUMBER,\n");
        builder.append("purchase_date TIMESTAMP,\n");
        builder.append("price NUMBER NOT NULL,\n");
        builder.append("quantity NUMBER NOT NULL,\n");
        builder.append("CONSTRAINT supplier_provides_pk PRIMARY KEY (id_supplier, id_product, purchase_date),\n");
        builder.append("CONSTRAINT price_supplies_ck CHECK (price > 0),\n");
        builder.append("CONSTRAINT quantity_supplies CHECK (quantity > 0)\n");
        builder.append(");");

        System.out.println(builder.toString());
        return builder.toString();
    }
}
