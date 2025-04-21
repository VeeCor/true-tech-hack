package dev.truetechhack.app.implementation.product;

import dev.truetechhack.app.api.TrueTabsRepository;
import dev.truetechhack.app.api.product.FindProductByNameInbound;
import dev.truetechhack.app.exception.ProductException;
import dev.truetechhack.domain.product.Product;
import dev.truetechhack.domain.response.ApiResponse;
import dev.truetechhack.domain.response.DataWrapper;
import dev.truetechhack.domain.response.RecordsWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindProductByNameUseCase implements FindProductByNameInbound {
    private static final String PATH = "/dstXFd44w7UBFj1Hto/records?viewId=viweo1seC0aMx&fieldKey=name";
    private final TrueTabsRepository trueTabsRepository;

    @Override
    public Flux<Product> execute(String name) {
        if (name == null || name.isEmpty()) {
            throw new ProductException("Name is null or empty");
        }
        return trueTabsRepository.get(PATH,
                new ParameterizedTypeReference<ApiResponse<DataWrapper<Product>>>() {
                })
            .flatMapMany(response -> {
                if (response == null || response.getData() == null || response.getData().getRecords() == null) {
                    return Flux.error(new ProductException("Empty response or data"));
                }

                log.info("code = {}, success = {}, message = {}",
                    response.getCode(), response.isSuccess(), response.getMessage());

                List<Product> matched = response.getData().getRecords().stream()
                    .map(RecordsWrapper::getFields)
                    .filter(product -> product.getName() != null && product.getName().contains(name))
                    .toList();

                if (matched.isEmpty()) {
                    return Flux.error(new ProductException("No products found with name: " + name));
                }

                return Flux.fromIterable(matched);
            });
    }
}
