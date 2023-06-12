package br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.sale;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.customer.Customer;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product.Product;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.sale.Sale;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao.SaleDAO;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.customer.FindCustomerUseCase;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.product.FindProductUseCase;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class FindSaleUseCase {
    private final SaleDAO saleDAO;
    private final FindCustomerUseCase findCustomerUseCase;
    private final FindProductUseCase findProductUseCase;

    public FindSaleUseCase(SaleDAO saleDAO, FindCustomerUseCase findCustomerUseCase,
                           FindProductUseCase findProductUseCase) {
        this.saleDAO = saleDAO;
        this.findCustomerUseCase = findCustomerUseCase;
        this.findProductUseCase = findProductUseCase;
    }

    public Optional<Sale> findOneById(long id) {
        if (id <= 0)
            throw new IllegalArgumentException("Id must be a positive number!");

        return saleDAO.findOneByKey(id);
    }

    public Optional<Sale> findOneByCustomerIdAndDateTime(long customerId, LocalDateTime issueDate) {
        Objects.requireNonNull(issueDate);

        Customer customer = findCustomerUseCase.findOneById(customerId)
                                .orElseThrow(() -> new EntityNotFoundException("There is not such customer!"));

        return saleDAO.findOneByCustomerAndDate(customer, issueDate);
    }

    public Map<Long, Sale> findSomeByPeriod(LocalDateTime start, LocalDateTime end) {
        Objects.requireNonNull(start);
        Objects.requireNonNull(end);

        if (!start.isBefore(end))
            throw new IllegalArgumentException("The start date must be before the end!");

        return saleDAO.findSomeByPeriod(start, end);
    }

    public Map<Long, Sale> findSomeByCustomerId(long customerId) {
        Customer customer = findCustomerUseCase.findOneById(customerId)
                                .orElseThrow(() -> new EntityNotFoundException("There is not such customer!"));

        return saleDAO.findSomeByCustomer(customer);
    }

    public Map<Long, Sale> findSomeByProductId(long productId) {
        Product product = findProductUseCase.findOneById(productId)
                            .orElseThrow(() -> new EntityNotFoundException("There is not such product!"));

        return saleDAO.findSomeByProduct(product);
    }

    public Map<Long, Sale> findAll() {
        return saleDAO.findAll();
    }
}
