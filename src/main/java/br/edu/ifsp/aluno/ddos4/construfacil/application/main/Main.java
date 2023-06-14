package br.edu.ifsp.aluno.ddos4.construfacil.application.main;

import br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.dao.CustomerSQLiteDAO;
import br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.dao.ProductSQLiteDAO;
import br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.dao.SupplierSQLiteDAO;
import br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.util.SQLiteDatabaseBuilder;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.customer.Customer;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product.Product;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.supplier.Supplier;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.customer.RegistryCustomerUseCase;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.product.RegistryProductUseCase;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.supplier.RegistrySupplierUseCase;

public class Main{
    public static void main(String[] args) {
        SQLiteDatabaseBuilder db = new SQLiteDatabaseBuilder();
        db.buildDatabaseIfMissing();

//        CustomerSQLiteDAO customerSQLiteDAO = new CustomerSQLiteDAO();
//        RegistryCustomerUseCase registryCustomerUseCase = new RegistryCustomerUseCase(customerSQLiteDAO);
//        Customer c1 = new Customer("Alexandre", "123.456.789-89", "Av. São Carlos", "(16) 98416-5135");
//        registryCustomerUseCase.registry(c1);
//
//        SupplierSQLiteDAO supplierSQLiteDAO = new SupplierSQLiteDAO();
//        RegistrySupplierUseCase registrySupplierUseCase = new RegistrySupplierUseCase(supplierSQLiteDAO);
//        Supplier s1 = new Supplier("Corussant", "12348913", "(16) 1111-5555");
//        registrySupplierUseCase.registry(s1);
//
//        ProductSQLiteDAO productSQLiteDAO = new ProductSQLiteDAO();
//        RegistryProductUseCase productUseCase = new RegistryProductUseCase(productSQLiteDAO);
//        Product p1 = new Product("Martelo", 20, 20L);
//        productUseCase.registry(p1);

    }
}
