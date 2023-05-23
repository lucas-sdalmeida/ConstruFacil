package br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase;

public class PurchaseRefund {
    private Long id;
    private final Purchase purchase;

    public PurchaseRefund(Long id, Purchase purchase) {
        this.id = id;
        this.purchase = purchase;
    }

    public PurchaseRefund(Purchase purchase) {
        this.purchase = purchase;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Purchase getPurchase() {
        return purchase;
    }
}
