package br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.supplier;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.supplier.Supplier;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao.SupplierDAO;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.EntityAlreadyExistsException;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.EntityNotFoundException;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.Notification;

import java.util.Objects;

public class RegistrySupplierUseCase {
    private final SupplierDAO supplierDAO;

    public RegistrySupplierUseCase(SupplierDAO supplierDAO) {
        this.supplierDAO = supplierDAO;
    }

    public Long registry(Supplier supplier) {
        Objects.requireNonNull(supplier);

        SupplierValidator validator = new SupplierValidator();
        Notification notification = validator.validate(supplier);

        if (notification.hasMessages())
            throw new IllegalArgumentException(notification.getMessagesAsString());

        supplierDAO.findOneByCorporateName(supplier.getCorporateName())
                .ifPresent(s -> {
                    throw new EntityAlreadyExistsException(
                        "A supplier with name '" + supplier.getCorporateName() +
                        "' has already been registered"
                    );
                });

        supplierDAO.save(supplier);
        long supplierId = supplierDAO
                                .findOneByCorporateName(supplier.getCorporateName())
                                .orElseThrow(() ->
                                        new EntityNotFoundException("The supplier has not been registered!")
                                )
                                .getId();

        supplier.setId(supplierId);

        return supplierId;
    }
}
