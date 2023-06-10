package br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.cashdesk;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.cashdesk.CashDesk;

public class GetCurrentBalance {
    public Long currentBalance(){
        CashDesk cashDesk = CashDesk.getInstance();
        return cashDesk.getCurrentBalanceInCents();
    }
}
