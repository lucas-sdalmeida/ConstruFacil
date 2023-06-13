package br.edu.ifsp.aluno.ddos4.construfacil.application.main;

import br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.dao.CustomerSQliteDAO;
import br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.dao.ProductSQliteDAO;
import br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.dao.SupplierSQliteDAO;
import br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.util.SQLiteDatabaseBuilder;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.customer.Customer;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product.Product;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.supplier.Supplier;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao.ProductDAO;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.util.DataBaseBuilder;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.customer.FindCustomerUseCase;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.customer.RegistryCustomerUseCase;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.customer.UpdateCustomerUseCase;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.product.FindProductUseCase;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.product.RegistryProductUseCase;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.product.UpdateProductUseCase;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.supplier.FindSupplierUseCase;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.supplier.RegistrySupplierUseCase;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.supplier.UpdateSupplierUseCase;


public class Main {
    public static void main(String[] args) {
//        SQLiteDatabaseBuilder db = new SQLiteDatabaseBuilder();
//        db.buildTables();
    }
}
