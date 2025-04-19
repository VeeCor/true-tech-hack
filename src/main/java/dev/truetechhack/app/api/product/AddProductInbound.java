package dev.truetechhack.app.api.product;

import dev.truetechhack.domain.product.Product;

public interface AddProductInbound {
    void execute(Product product);
}
