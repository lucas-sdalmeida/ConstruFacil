package br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.stock;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product.Product;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.util.filter.InRangeFilter;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.util.filter.MembershipFilter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Stock {
    private int lowQuantityAlertBorder;
    private static final Map<Long, Product> products = new HashMap<>();
    private final MembershipFilter<String> possibleNames = new MembershipFilter<>();
    private final InRangeFilter<Integer> quantityRangeFilter = new InRangeFilter<>();
    private final InRangeFilter<Double> defaultPurchasePriceFilter = new InRangeFilter<>();
    private final InRangeFilter<Double> defaultSalePriceFilter = new InRangeFilter<>();

    public Stock(int lowQuantityAlertBorder) {
        this.lowQuantityAlertBorder = lowQuantityAlertBorder;
    }

    public Stock(int lowQuantityAlertBorder, Product ...products) {
        this.lowQuantityAlertBorder = lowQuantityAlertBorder;
        storeSeveralProducts(products);
    }

    public void addPossibleName(String name) {
        possibleNames.addToAllowedValuesSet(name);
    }

    public void removePossibleName(String name) {
        possibleNames.removeFromAllowedValuesSet(name);
    }

    public void denyName(String name) {
        possibleNames.addToForbiddenValuesSet(name);
    }

    public void removeFromDeniedNames(String name) {
        possibleNames.removeFromForbiddenValuesSet(name);
    }

    public void addQuantityFilter(int value) {
        addQuantityRange(value, value);
    }

    public void addQuantityRange(int minValue, int maxValue) {
        quantityRangeFilter.setMinValue(minValue);
        quantityRangeFilter.setMaxValue(maxValue);
    }

    public void clearQuantityMinValue() {
        quantityRangeFilter.clearMinValue();
    }

    public void clearQuantityMaxValue() {
        quantityRangeFilter.clearMaxValue();
    }

    public void addDefaultPurchasePriceFilter(double value) {
        addDefaultPurchasePriceRange(value, value);
    }

    public void addDefaultPurchasePriceRange(double minValue, double maxValue) {
        defaultPurchasePriceFilter.setMinValue(minValue);
        defaultPurchasePriceFilter.setMaxValue(maxValue);
    }

    public void clearDefaultPurchasePriceMinValue() {
        defaultPurchasePriceFilter.clearMinValue();
    }

    public void clearDefaultPurchasePriceMaxValue() {
        defaultPurchasePriceFilter.clearMaxValue();
    }

    public void addDefaultSalePriceFilter(double value) {
        addDefaultSalePriceRange(value, value);
    }

    public void addDefaultSalePriceRange(double minValue, double maxValue) {
        defaultSalePriceFilter.setMinValue(minValue);
        defaultSalePriceFilter.setMaxValue(maxValue);
    }

    public void clearDefaultSalePriceMinValue() {
        defaultSalePriceFilter.clearMinValue();
    }

    public void clearDefaultSalePriceMaxValue() {
        defaultSalePriceFilter.clearMaxValue();
    }

    public boolean hasLowQuantityOf(Product product) {
        Objects.requireNonNull(product);

        if (!products.containsKey(product.getId()))
            throw new IllegalArgumentException("There's not such product in the stock");

        return product.getQuantity() <= lowQuantityAlertBorder;
    }

    public void storeProduct(Product product){
        Objects.requireNonNull(product);

        if (product.getId() == null)
            throw new IllegalArgumentException("This product is not registered in the system yet!");

        products.put(product.getId(), product);
    }

    public void storeSeveralProducts(Product ...products) {
        Objects.requireNonNull(products);

        for (Product product : products) {
            storeProduct(product);
        }
    }

    public Map<Long, Product> getUnfilteredProducts() {
        return Map.copyOf(products);
    }

    public Map<Long, Product> getUnfilteredProductsWithLowQuantity() {
        return products.values().stream()
                .filter(this::hasLowQuantityOf)
                .collect(Collectors.toMap(Product::getId, product -> product));
    }

    public Map<Long, Product> getFilteredProducts() {
        return products.values().stream()
                .filter(product -> possibleNames.applyTo(product.getName()))
                .filter(product -> quantityRangeFilter.applyTo(product.getQuantity()))
                .filter(product -> defaultPurchasePriceFilter.applyTo(product.getDefaultPurchasePrice()))
                .filter(product -> defaultSalePriceFilter.applyTo(product.getDefaultSalePrice()))
                .collect(Collectors.toMap(Product::getId, product -> product));
    }

    public Map<Long, Product> getFilteredProductsWithLowQuantity() {
        return products.values().stream()
                .filter(this::hasLowQuantityOf)
                .filter(product -> possibleNames.applyTo(product.getName()))
                .filter(product -> quantityRangeFilter.applyTo(product.getQuantity()))
                .filter(product -> defaultPurchasePriceFilter.applyTo(product.getDefaultPurchasePrice()))
                .filter(product -> defaultSalePriceFilter.applyTo(product.getDefaultSalePrice()))
                .collect(Collectors.toMap(Product::getId, product -> product));
    }

    public int getLowQuantityAlertBorder() {
        return lowQuantityAlertBorder;
    }

    public void setLowQuantityAlertBorder(int lowQuantityAlertBorder) {
        this.lowQuantityAlertBorder = lowQuantityAlertBorder;
    }
}
