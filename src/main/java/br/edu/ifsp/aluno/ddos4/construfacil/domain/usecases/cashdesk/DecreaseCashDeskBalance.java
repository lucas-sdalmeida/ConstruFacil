package br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.cashdesk;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.cashdesk.CashDesk;

public class DecreaseCashDeskBalance {
    Long currentBalanceInCents;

    public void decrease(long amoung){
        CashDesk cashDesk = CashDesk.getInstance();
        cashDesk.decreaseBalanceBy(amoung);
    }

}
