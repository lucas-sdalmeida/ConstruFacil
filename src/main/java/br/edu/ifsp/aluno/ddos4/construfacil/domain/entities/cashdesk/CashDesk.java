package br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.cashdesk;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase.Purchase;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase.PurchaseRefund;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.sale.Sale;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.sale.SaleRefund;

import java.util.List;

public class CashDesk {
    private Long id;
    private double initialBalance;
    private double finalBalance;
    private boolean cashIsOpen;
    private List<Sale> sales;
    private List<Purchase> purchases;
    private List<PurchaseRefund> refunds;
    private List<SaleRefund> saleRefunds;

    public CashDesk(Long id, double initialBalance, double finalBalance, boolean cashIsOpen, List<Sale> sales, List<Purchase> purchases, List<PurchaseRefund> refunds, List<SaleRefund> saleRefunds) {
        this.id = id;
        this.initialBalance = initialBalance;
        this.finalBalance = finalBalance;
        this.cashIsOpen = cashIsOpen;
        this.sales = sales;
        this.purchases = purchases;
        this.refunds = refunds;
        this.saleRefunds = saleRefunds;
    }

    public Long getId(){return id;}

    public void setId(Long id){ this.id = id;}

    public double getInitialBalance(){return initialBalance;}

    public void setInitialBalance(double initialBalance){this.initialBalance = initialBalance;}

    public double getFinalBalance(){return finalBalance;}

    public void setFinalBalance(double finalBalance){this.finalBalance = finalBalance;}

    public boolean getCashIsOpen(){return cashIsOpen;}

    public void setCashIsOpen(boolean cashIsOpen){this.cashIsOpen = cashIsOpen;}

    public List<Sale> getSales() {return sales;}

    public void setSales(List<Sale> sales) {this.sales = sales;}

    public List<Purchase> getPurchases() {return purchases;}

    public void setPurchases(List<Purchase> purchases) {this.purchases = purchases;}

    public List<PurchaseRefund> getRefunds() {return refunds;}

    public void setRefunds(List<PurchaseRefund> refunds) {this.refunds = refunds;}

    public List<SaleRefund> getSaleRefunds() {return saleRefunds;}

    public void setSaleRefunds(List<SaleRefund> saleRefunds) {this.saleRefunds = saleRefunds;}
}
