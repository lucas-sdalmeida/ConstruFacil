package br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.cashdesk;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase.Purchase;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase.PurchaseRefund;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.sale.Sale;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.sale.SaleRefund;

import java.util.List;

public class CashDesk {
    private Long id;
    private List<Sale> sales;
    private Long currentBalance;
    private List<Purchase> purchases;
    private List<PurchaseRefund> purchaseRefunds;
    private List<SaleRefund> saleRefunds;

    public CashDesk(Long id, List<Sale> sales, Long currentBalance, List<Purchase> purchases, List<PurchaseRefund> purchaseRefunds, List<SaleRefund> saleRefunds) {
        this.id = id;
        this.sales = sales;
        this.currentBalance = currentBalance;
        this.purchases = purchases;
        this.purchaseRefunds = purchaseRefunds;
        this.saleRefunds = saleRefunds;
    }

    public Long getId(){return id;}

    public void setId(Long id){ this.id = id;}

    public List<Sale> getSales() {return sales;}

    public void setSales(List<Sale> sales) {this.sales = sales;}

    public List<Purchase> getPurchases() {return purchases;}

    public void setPurchases(List<Purchase> purchases) {this.purchases = purchases;}

    public List<PurchaseRefund> getPurchaseRefunds() {return purchaseRefunds;}

    public void setPurchaseRefunds(List<PurchaseRefund> purchaseRefunds) {this.purchaseRefunds = purchaseRefunds;}

    public List<SaleRefund> getSaleRefunds() {return saleRefunds;}

    public void setSaleRefunds(List<SaleRefund> saleRefunds) {this.saleRefunds = saleRefunds;}
    public Long getCurrentBalance() {return currentBalance;}

    public void setCurrentBalance(Long currentBalance) {this.currentBalance = currentBalance;}
}
