package br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase;

public class Refund {
    private long id;
    private final Purchase purchase;

    public Refund(long id, Purchase purchase) {
        this.id = id;
        this.purchase = purchase;
    }

    public Refund(Purchase purchase) {
        this.purchase = purchase;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Purchase getPurchase() {
        return purchase;
    }
}
