package dev.truetechhack.adapter.rest.product;

import dev.truetechhack.app.api.product.GetAllProductsInbound;
import dev.truetechhack.domain.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest-api/product")
@RequiredArgsConstructor
public class ProductController {
    private final GetAllProductsInbound getAllProductsInbound;

    @GetMapping("/getAll")
    public List<Product> getAllProducts() {
        return getAllProductsInbound.execute();
    }
}
