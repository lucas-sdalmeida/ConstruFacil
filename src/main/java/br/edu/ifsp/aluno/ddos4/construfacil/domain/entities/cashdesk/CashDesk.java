package br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.cashdesk;

import java.util.Objects;

public class CashDesk {
    private static CashDesk INSTANCE = null;
    private Long currentBalanceInCents;

    private CashDesk() {
        this.currentBalanceInCents = 0L;
    }

    public static CashDesk getInstance() {
        if (INSTANCE == null)
            INSTANCE = new CashDesk();

        return INSTANCE;
    }

    public Long getCurrentBalanceInCents() {
        return currentBalanceInCents;
    }

    public void increaseBalanceBy(long amountInCents) {
        if (amountInCents < 0)
            throw new IllegalArgumentException("The amount must be a non-negative number!");
        currentBalanceInCents += amountInCents;
    }

    public void decreaseBalanceBy(long amountInCents) {
        if (amountInCents < 0)
            throw new IllegalArgumentException("The amount must be a non-negative number!");
        currentBalanceInCents -= amountInCents;
    }
}
