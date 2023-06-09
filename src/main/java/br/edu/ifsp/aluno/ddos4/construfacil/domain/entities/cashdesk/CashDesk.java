package br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.cashdesk;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase.Purchase;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase.PurchaseRefund;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.sale.Sale;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.sale.SaleRefund;

import java.util.List;

public class CashDesk {
    private Long currentBalance;

    public CashDesk(Long currentBalance) {
        this.currentBalance = currentBalance;
    }

    public Long getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(Long currentBalance) {
        this.currentBalance = currentBalance;
    }
}
