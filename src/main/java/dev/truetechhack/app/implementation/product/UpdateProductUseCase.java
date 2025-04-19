package dev.truetechhack.app.implementation.product;

import dev.truetechhack.app.api.TrueTabsRepository;
import dev.truetechhack.app.api.product.UpdateProductInbound;
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
public class UpdateProductUseCase implements UpdateProductInbound {
    private static final String PATH = "/dstXFd44w7UBFj1Hto/records?viewId=viweo1seC0aMx&fieldKey=name";
    private final TrueTabsRepository trueTabsRepository;

    @Override
    public void execute(Map<String, Object> field) {
        trueTabsRepository.get(PATH,
                new ParameterizedTypeReference<ApiResponse<DataWrapper<Product>>>() {
                })
            .flatMap(response -> {
                String recordId = response.getData().getRecords().stream()
                    .filter(record -> field.get("id").equals(record.getFields().getId()))
                    .findFirst()
                    .orElseThrow(() -> new ProductException("Product not found"))
                    .getRecordId();

                field.remove("id");

                Map<String, Object> body = Map.of(
                    "records", List.of(
                        Map.of(
                            "recordId", recordId,
                            "fields", field
                        )
                    )
                );

                return trueTabsRepository.patch(PATH, body,
                    new ParameterizedTypeReference<ApiResponse<DataWrapper<Product>>>() {
                    });
            })
            .doOnNext(response -> {
                System.out.println(response.getData());
                log.info("code = {}, success = {}, message = {}", response.getCode(), response.isSuccess(), response.getMessage());
            })
            .doOnError(error -> {
                throw new ProductException("Failed to update product: " + error.getMessage());
            })
            .subscribe();
    }
}
