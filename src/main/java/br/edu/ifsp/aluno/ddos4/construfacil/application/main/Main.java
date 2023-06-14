package br.edu.ifsp.aluno.ddos4.construfacil.application.main;

import br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.dao.CustomerSQLiteDAO;
import br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.util.SQLiteDatabaseBuilder;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.customer.Customer;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.customer.RegistryCustomerUseCase;

public class Main{
    public static void main(String[] args) {
        SQLiteDatabaseBuilder db = new SQLiteDatabaseBuilder();
        db.buildDatabaseIfMissing();

        CustomerSQLiteDAO customerSQLiteDAO = new CustomerSQLiteDAO();
        RegistryCustomerUseCase registryCustomerUseCase = new RegistryCustomerUseCase(customerSQLiteDAO);
        Customer c1 = new Customer("Alexandre", "123.456.789-89", "Av. São Carlos", "(16) 98416-5135");

        registryCustomerUseCase.registry(c1);

    }
}
