package br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.sale;

public class SaleRefund {
    private long id;
    private final Sale sale;

    public SaleRefund(long id, Sale sale) {
        this.id = id;
        this.sale = sale;
    }

    public SaleRefund(Sale sale) {
        this.sale = sale;
    }

    public long getId() {return id;}

    public void setId(long id) {this.id = id;}

    public Sale getSale() {
        return sale;
    }
}
