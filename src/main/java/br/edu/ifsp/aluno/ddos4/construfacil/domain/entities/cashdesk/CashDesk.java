package br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.cashdesk;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase.Purchase;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase.Refund;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.sale.Sale;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.sale.SaleRefund;

import java.util.List;

public class CashDesk {
    private List<Sale> sales;
    private List<Purchase> purchases;
    private List<Refund> refunds;
    private List<SaleRefund> saleRefunds;

    public CashDesk(List<Sale> sales, List<Purchase> purchases, List<Refund> refunds, List<SaleRefund> saleRefunds) {
        this.sales = sales;
        this.purchases = purchases;
        this.refunds = refunds;
        this.saleRefunds = saleRefunds;
    }

    public List<Sale> getSales() {return sales;}

    public void setSales(List<Sale> sales) {this.sales = sales;}

    public List<Purchase> getPurchases() {return purchases;}

    public void setPurchases(List<Purchase> purchases) {this.purchases = purchases;}

    public List<Refund> getRefunds() {return refunds;}

    public void setRefunds(List<Refund> refunds) {this.refunds = refunds;}

    public List<SaleRefund> getSaleRefunds() {return saleRefunds;}

    public void setSaleRefunds(List<SaleRefund> saleRefunds) {this.saleRefunds = saleRefunds;}
}
