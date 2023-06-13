package br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.sale;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.sale.Sale;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.sale.SaleItem;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.sale.SaleRefund;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao.SaleRefundDAO;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.EntityAlreadyExistsException;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.EntityNotFoundException;

public class CreateSaleRefundUseCase {
    private final SaleRefundDAO saleRefundDAO;
    private final FindSaleUseCase findSaleUseCase;

    public CreateSaleRefundUseCase(SaleRefundDAO saleRefundDAO, FindSaleUseCase findSaleUseCase) {
        this.saleRefundDAO = saleRefundDAO;
        this.findSaleUseCase = findSaleUseCase;
    }

    public Long createRefund(long saleId) {
        Sale sale = findSaleUseCase.findOneById(saleId)
                .orElseThrow(() -> new EntityNotFoundException("There is not such sale!"));

        saleRefundDAO.findOneBySale(sale)
                .ifPresent(s -> {
                    throw new EntityAlreadyExistsException("There already is a refund for this Sale!");
                });

        SaleRefund refund = new SaleRefund(sale);
        saleRefundDAO.save(refund);
        long refundId = saleRefundDAO.findOneBySale(sale)
                            .orElseThrow(() -> new EntityNotFoundException("Could not save the refund!"))
                            .getId();
        refund.setId(refundId);
        updateProducts(sale);

        return refundId;
    }

    private void updateProducts(Sale sale) {
        sale.getSaleItemsList()
                .stream()
                .map(SaleItem::getProduct)
                .distinct()
                .forEach(product -> product.increaseStockQuantityBy(sale.getProductQuantity(product)));
    }
}
