package dev.truetechhack.app.implementation.product;

import dev.truetechhack.app.api.TrueTabsRepository;
import dev.truetechhack.app.api.product.FindProductByIdInbound;
import dev.truetechhack.app.exception.ProductException;
import dev.truetechhack.domain.product.Product;
import dev.truetechhack.domain.response.ApiResponse;
import dev.truetechhack.domain.response.DataWrapper;
import dev.truetechhack.domain.response.RecordsWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindProductByIdUseCase implements FindProductByIdInbound {
    private static final String PATH = "/dstXFd44w7UBFj1Hto/records?viewId=viweo1seC0aMx&fieldKey=name";
    private final TrueTabsRepository trueTabsRepository;

    @Override
    public Mono<Product> execute(int id) {
        return trueTabsRepository.get(PATH,
                new ParameterizedTypeReference<ApiResponse<DataWrapper<Product>>>() {
                })
            .flatMap(response -> {
                if (response == null || response.getData() == null || response.getData().getRecords() == null) {
                    return Mono.error(new ProductException("Empty response or data"));
                }

                log.info("code = {}, success = {}, message = {}",
                    response.getCode(), response.isSuccess(), response.getMessage());

                return response.getData().getRecords().stream()
                    .map(RecordsWrapper::getFields)
                    .filter(product -> product.getId() == id)
                    .findFirst()
                    .map(Mono::just)
                    .orElseGet(() -> Mono.error(new ProductException("Product with id " + id + " not found")));
            });
    }
}
