package br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.supplier;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.supplier.Supplier;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao.SupplierDAO;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.EntityNotFoundException;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.Notification;

import java.util.Objects;

public class UpdateSupplierUseCase {
    private final SupplierDAO supplierDAO;

    public UpdateSupplierUseCase(SupplierDAO supplierDAO) {
        this.supplierDAO = supplierDAO;
    }

    public void updateSupplier(Supplier supplier) {
        Objects.requireNonNull(supplier);

        SupplierValidator validator = new SupplierValidator();
        Notification notification = validator.validate(supplier);

        if (notification.hasMessages())
            throw new IllegalArgumentException(notification.getMessagesAsString());

        supplierDAO.findOneByKey(supplier.getId())
                    .orElseThrow(() -> new EntityNotFoundException("There is not such supplier!"));

        supplierDAO.update(supplier);
    }
}
