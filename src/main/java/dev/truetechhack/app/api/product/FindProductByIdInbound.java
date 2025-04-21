package dev.truetechhack.app.api.product;

import dev.truetechhack.domain.product.Product;
import reactor.core.publisher.Mono;

public interface FindProductByIdInbound {
    Mono<Product> execute(int id);
}
