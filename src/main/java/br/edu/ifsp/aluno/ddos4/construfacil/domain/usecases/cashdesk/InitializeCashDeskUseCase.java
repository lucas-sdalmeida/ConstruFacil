package br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.cashdesk;

/*
InitializeCashDeskUseCase: fazer uma consulta nas tabelas de suppllier_provides e
customer_buys e obter e calcular o saldo atual do caixa
*/

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.cashdesk.CashDesk;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao.*;

public class InitializeCashDeskUseCase  {
    private PurchaseDAO purchaseDAO;
    private SaleDAO saleDAO;

    private SaleRefundDAO saleRefundDAO;

    private PurchaseRefundDAO productRefundDAO;


    public InitializeCashDeskUseCase(PurchaseDAO purchaseDAO, SaleDAO saleDAO, SaleRefundDAO saleRefundDAO, PurchaseRefundDAO productRefundDAO) {
        this.purchaseDAO = purchaseDAO;
        this.saleDAO = saleDAO;
        this.saleRefundDAO = saleRefundDAO;
        this.productRefundDAO = productRefundDAO;
    }

    public Long initialize(){
        long purchaseTotalValue = purchaseDAO.getTotalPrice();
        long purchaseRefundTotalValue = productRefundDAO.getTotalPrice();
        long saleTotalValue = saleDAO.getTotaPrice();
        long saleRefundTotalValue = saleRefundDAO.getTotalPrice();

        CashDesk cashDeskInstance = CashDesk.getInstance();
        cashDeskInstance.decreaseBalanceBy(purchaseTotalValue + saleRefundTotalValue);
        cashDeskInstance.increaseBalanceBy(saleTotalValue + purchaseRefundTotalValue);

        return cashDeskInstance.getCurrentBalanceInCents();
    }


}
