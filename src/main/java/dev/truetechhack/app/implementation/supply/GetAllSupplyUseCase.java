package dev.truetechhack.app.implementation.supply;

import dev.truetechhack.app.api.TrueTabsRepository;
import dev.truetechhack.app.api.supply.GetAllSupplyInbound;
import dev.truetechhack.app.exception.SupplyException;
import dev.truetechhack.domain.response.ApiResponse;
import dev.truetechhack.domain.response.DataWrapper;
import dev.truetechhack.domain.response.RecordsWrapper;
import dev.truetechhack.domain.supply.Supply;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetAllSupplyUseCase implements GetAllSupplyInbound {
    private static final String PATH = "/dstvQqtRDwrBWjSQWA/records?viewId=viwPxupgqT0FT&fieldKey=name";
    private final TrueTabsRepository trueTabsRepository;

    @Override
    public Flux<Supply> execute() {
    return trueTabsRepository.get(PATH, new ParameterizedTypeReference<ApiResponse<DataWrapper<Supply>>>() {
    }).flatMapMany(response -> {
        if (response != null) {
            log.info("code = {}, success = {}, message = {}", response.getCode(), response.isSuccess(), response.getMessage());
            return Flux.fromIterable(response.getData().getRecords().stream().map(RecordsWrapper::getFields).toList());
        }
        else {
            return Flux.error(new SupplyException("Response is null"));
        }
    });
    }
}
