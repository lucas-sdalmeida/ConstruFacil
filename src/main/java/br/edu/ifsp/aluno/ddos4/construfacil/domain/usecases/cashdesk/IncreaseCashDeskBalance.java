package br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.cashdesk;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.cashdesk.CashDesk;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao.SaleDAO;

public class IncreaseCashDeskBalance {
    public void increase(long amount){
        CashDesk cashDesk = CashDesk.getInstance();
        cashDesk.increaseBalanceBy(amount);
    }
}
