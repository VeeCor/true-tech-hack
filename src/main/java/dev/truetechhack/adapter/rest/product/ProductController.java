package dev.truetechhack.adapter.rest.product;

import dev.truetechhack.app.api.product.*;
import dev.truetechhack.app.exception.ProductException;
import dev.truetechhack.domain.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/rest-api/product")
@RequiredArgsConstructor
public class ProductController {
    private final GetAllProductsInbound getAllProductsInbound;
    private final AddProductInbound addProductInbound;
    private final UpdateProductInbound updateProductInbound;
    private final FindProductByIdInbound findProductByIdInbound;
    private final FindProductByNameInbound findProductByNameInbound;

    @GetMapping("/getAll")
    public Flux<Product> getAllProducts() {
        return getAllProductsInbound.execute();
    }

    @GetMapping("/findById/{id}")
    public Mono<Product> findById(@PathVariable int id) {
        return findProductByIdInbound.execute(id);
    }

    @GetMapping("/findByName/{name}")
    public Flux<Product> findByName(@PathVariable String name) {
        return findProductByNameInbound.execute(name);
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

    @PatchMapping("/update")
    public Mono<ResponseEntity<String>> updateProduct(@RequestBody Map<String, Object> field) {
        return Mono.fromCallable(() -> {
                updateProductInbound.execute(field);
                return ResponseEntity.ok("Product update successfully"); })
            .onErrorResume(ProductException.class, e ->
                Mono.just(ResponseEntity.badRequest().body("Failed to update product: " + e.getMessage()))
            );
    }
}
