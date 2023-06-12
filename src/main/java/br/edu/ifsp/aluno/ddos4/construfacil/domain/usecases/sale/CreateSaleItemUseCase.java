package br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.sale;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product.Product;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.sale.SaleItem;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.product.FindProductUseCase;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.EntityNotFoundException;

public class CreateSaleItemUseCase {
    private final FindProductUseCase findProductUseCase;

    public CreateSaleItemUseCase(FindProductUseCase findProductUseCase) {
        this.findProductUseCase = findProductUseCase;
    }

    public SaleItem createSaleItem(long productId, long priceInCents) {
        Product product = findProductUseCase.findOneById(productId)
                            .orElseThrow(() -> new EntityNotFoundException("There is not such product!"));

        if (priceInCents <= product.getAveragePurchasePriceInCents())
            throw new IllegalArgumentException("The sale price must be greater than the product average" +
                            " purchase price!");

        return new SaleItem(product, priceInCents);
    }
}
