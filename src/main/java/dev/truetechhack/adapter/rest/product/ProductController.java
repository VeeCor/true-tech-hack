package dev.truetechhack.adapter.rest.product;

import dev.truetechhack.app.api.product.AddProductInbound;
import dev.truetechhack.app.api.product.GetAllProductsInbound;
import dev.truetechhack.app.exception.ProductException;
import dev.truetechhack.domain.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/rest-api/product")
@RequiredArgsConstructor
public class ProductController {
    private final GetAllProductsInbound getAllProductsInbound;
    private final AddProductInbound addProductInbound;

    @GetMapping("/getAll")
    public Flux<Product> getAllProducts() {
        return getAllProductsInbound.execute();
    }

    @PostMapping("/add-product")
    public Mono<ResponseEntity<String>> addProduct(@RequestBody Product product) {
        return Mono.fromCallable(() -> {
                addProductInbound.execute(product);
                return ResponseEntity.ok("Product added successfully"); })
            .onErrorResume(ProductException.class, e ->
                Mono.just(ResponseEntity.badRequest().body("Failed to add product: " + e.getMessage()))
            );
    }
}
