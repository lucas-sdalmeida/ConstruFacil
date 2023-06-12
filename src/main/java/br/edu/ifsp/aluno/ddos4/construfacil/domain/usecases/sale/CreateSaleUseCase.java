package br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.sale;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.customer.Customer;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.sale.Sale;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.sale.SaleItem;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao.SaleDAO;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.customer.FindCustomerUseCase;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.EntityAlreadyExistsException;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.EntityNotFoundException;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.Notification;

import java.time.LocalDateTime;
import java.util.Objects;

public class CreateSaleUseCase {
    private final SaleDAO saleDAO;
    private final FindCustomerUseCase findCustomerUseCase;
    private Sale sale;

    private CreateSaleUseCase(SaleDAO saleDAO, FindCustomerUseCase findCustomerUseCase) {
        this.saleDAO = saleDAO;
        this.findCustomerUseCase = findCustomerUseCase;
    }

    public static CreateSaleUseCase beginSale(SaleDAO saleDAO, FindCustomerUseCase findCustomerUseCase) {
        Objects.requireNonNull(saleDAO);
        Objects.requireNonNull(findCustomerUseCase);

        CreateSaleUseCase instance = new CreateSaleUseCase(saleDAO, findCustomerUseCase);
        instance.sale = new Sale();

        return instance;
    }

    public Long finishAndSaveSale() {
        SaleValidator validator = new SaleValidator();
        Notification notification = validator.validate(sale);

        if (notification.hasMessages())
            throw new IllegalStateException(notification.getMessagesAsString());

        if (sale.getIssueDate() != null)
            sale.setIssueDate(LocalDateTime.now());

        saleDAO.findOneByCustomerAndDate(sale.getCustomer(), sale.getIssueDate())
                .ifPresent(sale -> {
                    throw new EntityAlreadyExistsException("There already is a sale with this customer at this " +
                            "date and time!");
                });

        saleDAO.save(sale);
        long saleId = saleDAO.findOneByCustomerAndDate(sale.getCustomer(), sale.getIssueDate())
                            .orElseThrow(() -> new EntityNotFoundException("Could not save this sale!"))
                            .getId();
        sale.setId(saleId);

        return saleId;
    }

    public void assignCustomerById(long customerId) {
        Customer customer = findCustomerUseCase.findOneById(customerId)
                                .orElseThrow(() -> new EntityNotFoundException("There is not such customer!"));

        sale.setCustomer(customer);
    }

    public void addSaleItem(SaleItem item) {
        sale.addSaleItem(item);
    }

    public void increaseSaleItemQuantityBy(SaleItem item, long amount) {
        sale.increaseSaleItemQuantityBy(item, amount);
    }

    public void increaseSaleItemQuantityByOne(SaleItem item) {
        sale.increaseSaleItemQuantityBy(item, 1L);
    }

    public void removeSaleItem(SaleItem item) {
        sale.removeSaleItem(item);
    }

    public void decreaseSaleItemQuantityBy(SaleItem item, long amount) {
        sale.decreaseSaleItemQuantityBy(item, amount);
    }

    public void decreaseSaleItemQuantityByOne(SaleItem item) {
        sale.increaseSaleItemQuantityBy(item, 1L);
    }
}
