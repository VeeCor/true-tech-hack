package dev.truetechhack.app.implementation.product;

import dev.truetechhack.app.api.TrueTabsRepository;
import dev.truetechhack.app.api.product.AddProductInbound;
import dev.truetechhack.app.exception.ProductException;
import dev.truetechhack.domain.product.Product;
import dev.truetechhack.domain.response.ApiResponse;
import dev.truetechhack.domain.response.DataWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class AddProductUseCase implements AddProductInbound {
    private static final String PATH = "/dstXFd44w7UBFj1Hto/records?viewId=viweo1seC0aMx&fieldKey=name";
    private final TrueTabsRepository trueTabsRepository;

    @Override
    public void execute(Product product) {
        Map<String, Object> body = Map.of(
            "records", List.of(
                Map.of("fields", Map.of(
                    "id", product.getId(),
                    "name", product.getName(),
                    "price", product.getPrice(),
                    "size", product.getSize(),
                    "count", product.getCount(),
                    "location", product.getLocation()
                ))
            ),
            "fieldKey", "name"
        );

        trueTabsRepository.post(
            PATH, body, new ParameterizedTypeReference<ApiResponse<DataWrapper<Product>>>() {})
            .doOnNext(response -> {
            log.info("code = {}, success = {}, message = {}", response.getCode(), response.isSuccess(), response.getMessage());
        }).doOnError(error -> {
            throw new ProductException("Failed to add product: " + error.getMessage());
        }).subscribe();
    }
}
