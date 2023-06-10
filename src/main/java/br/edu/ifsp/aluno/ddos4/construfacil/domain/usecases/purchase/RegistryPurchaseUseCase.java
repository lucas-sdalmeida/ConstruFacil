package br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.purchase;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase.Purchase;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao.PurchaseDAO;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.EntityAlreadyExistsException;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.EntityNotFoundException;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.Notification;

import java.util.Objects;

public class RegistryPurchaseUseCase {
    private final PurchaseDAO purchaseDAO;

    public RegistryPurchaseUseCase(PurchaseDAO purchaseDAO) {this.purchaseDAO = purchaseDAO;}

    public Long registry(Purchase purchase){
        Objects.requireNonNull(purchase);

        purchaseDAO.findOneByKey(purchase.getId())
                .ifPresent(c -> {
                    throw new EntityAlreadyExistsException(
                        "A Purchase with ID " + purchase.getId() +
                        "has already been registred."
                    );
                });
        purchaseDAO.save(purchase);
        long purchaseId = purchaseDAO
                .findOneByKey(purchase.getId())
                .orElseThrow(() ->
                        new EntityNotFoundException("The purchase has not been registred!")
                ).getId();

        return purchaseId;
    }
}
