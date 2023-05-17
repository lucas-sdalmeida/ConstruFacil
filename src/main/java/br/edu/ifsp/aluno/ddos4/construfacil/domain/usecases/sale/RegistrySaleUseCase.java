package br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.sale;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.sale.Sale;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao.SaleDAO;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.EntityAlreadyExistsException;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.EntityNotFoundException;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.Notification;

import java.util.Objects;

public class RegistrySaleUseCase{
    private final SaleDAO saleDAO;

    public RegistrySaleUseCase(SaleDAO saleDAO) {this.saleDAO = saleDAO;}

    public Long registry(Sale sale){
        Objects.requireNonNull(sale);

        SaleValidator validator = new SaleValidator();
        Notification notification = validator.validate(sale);

        if (notification.hasMessages())
            throw new IllegalArgumentException(notification.getMessagesAsString());

        saleDAO.findOneByKey(sale.getId())
                .ifPresent(s -> {
                    throw new EntityAlreadyExistsException(
                            "A sale with id " + sale.getId() +
                                    " has already been registered"
                    );
                });

        saleDAO.save(sale);
        long saleId = saleDAO
                .findOneByKey(sale.getId())
                .orElseThrow(() ->
                        new EntityNotFoundException("The supplier has not been registred!")
                ).getId();
        return saleId;
        }
    }
