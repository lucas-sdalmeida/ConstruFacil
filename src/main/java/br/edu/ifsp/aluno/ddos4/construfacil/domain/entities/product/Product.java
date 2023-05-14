package br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product;

public class Product {
    private long id;
    private String name;
    private int quantity;
    private double purchasePrice;
    private double purchaseSale;

    public Product(long id, String name, int quantity, double purchasePrice, double purchaseSale) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
        this.purchaseSale = purchaseSale;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPurchasePrice() {return purchasePrice;}

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getPurchaseSale() {
        return purchaseSale;
    }

    public void setPurchaseSale(double purchaseSale) {
        this.purchaseSale = purchaseSale;
    }
}
