package dev.truetechhack.app.api.product;

import dev.truetechhack.domain.product.Product;
import reactor.core.publisher.Flux;

public interface FindProductByNameInbound {
    Flux<Product> execute(String name);
}
