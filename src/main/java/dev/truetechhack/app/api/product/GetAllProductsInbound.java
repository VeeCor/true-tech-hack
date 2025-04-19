package dev.truetechhack.app.api.product;

import dev.truetechhack.domain.product.Product;
import reactor.core.publisher.Flux;

import java.util.List;

public interface GetAllProductsInbound {
    Flux<Product> execute();
}
