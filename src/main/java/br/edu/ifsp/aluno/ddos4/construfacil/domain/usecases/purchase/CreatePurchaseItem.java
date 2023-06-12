package br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.purchase;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product.Product;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase.PurchaseItem;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.product.FindProductUseCase;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.EntityNotFoundException;

public class CreatePurchaseItem {
    private final FindProductUseCase findProductUseCase;

    public CreatePurchaseItem(FindProductUseCase findProductUseCase) {
        this.findProductUseCase = findProductUseCase;
    }

    public PurchaseItem createPurchaseItem(long productId, long priceInCents) {
        Product product = findProductUseCase.findOneById(productId)
                            .orElseThrow(() -> new EntityNotFoundException("There is not such product!"));

        return new PurchaseItem(product, priceInCents);
    }
}
