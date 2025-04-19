package dev.truetechhack.app.implementation.product;

import dev.truetechhack.app.api.TrueTabsRepository;
import dev.truetechhack.app.api.product.GetAllProductsInbound;
import dev.truetechhack.app.exception.ProductException;
import dev.truetechhack.domain.product.Product;
import dev.truetechhack.domain.response.ApiResponse;
import dev.truetechhack.domain.response.DataWrapper;
import dev.truetechhack.domain.response.RecordsWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetAllProductsUseCase implements GetAllProductsInbound {
    private static final String PATH = "/dstXFd44w7UBFj1Hto/records?viewId=viweo1seC0aMx&fieldKey=name";
    private final TrueTabsRepository trueTabsRepository;

    @Override
    public List<Product> execute() {

        ApiResponse<DataWrapper<Product>> response = trueTabsRepository.get(PATH,
                new ParameterizedTypeReference<ApiResponse<DataWrapper<Product>>>() {
                })
            .block();
        System.out.println(response);

        if (response != null) {
            log.info("code = {}, success = {}, message = {}", response.getCode(), response.isSuccess(), response.getMessage());
            return response.getData().getRecords().stream().map(RecordsWrapper::getFields).toList();
        } else {
            throw new ProductException("Response is null");
        }
    }
}
