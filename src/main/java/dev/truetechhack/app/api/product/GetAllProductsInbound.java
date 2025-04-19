package dev.truetechhack.app.api.product;

import dev.truetechhack.domain.product.Product;

import java.util.List;

public interface GetAllProductsInbound {
    List<Product> execute();
}
