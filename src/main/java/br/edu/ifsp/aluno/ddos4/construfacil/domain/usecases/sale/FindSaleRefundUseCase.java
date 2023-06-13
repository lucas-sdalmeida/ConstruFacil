package br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.sale;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.sale.Sale;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.sale.SaleRefund;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao.SaleRefundDAO;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.EntityNotFoundException;

import java.util.Map;
import java.util.Optional;

public class FindSaleRefundUseCase {
    private final SaleRefundDAO saleRefundDAO;
    private final FindSaleUseCase findSaleUseCase;

    public FindSaleRefundUseCase(SaleRefundDAO saleRefundDAO, FindSaleUseCase findSaleUseCase) {
        this.saleRefundDAO = saleRefundDAO;
        this.findSaleUseCase = findSaleUseCase;
    }

    public Optional<SaleRefund> findOneById(long refundId) {
        if (refundId <= 0)
            throw new IllegalArgumentException("The is must be a positive number");

        return saleRefundDAO.findOneByKey(refundId);
    }

    public Optional<SaleRefund> findOneBySaleId(long saleId) {
        Sale sale = findSaleUseCase.findOneById(saleId)
                .orElseThrow(() -> new EntityNotFoundException("There is not such sale!"));

        return saleRefundDAO.findOneBySale(sale);
    }

    public Map<Long, SaleRefund> findAll() {
        return saleRefundDAO.findAll();
    }
}
