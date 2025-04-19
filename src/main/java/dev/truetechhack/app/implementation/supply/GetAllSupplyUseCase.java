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

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetAllSupplyUseCase implements GetAllSupplyInbound {
    private static final String PATH = "/dstvQqtRDwrBWjSQWA/records?viewId=viwPxupgqT0FT&fieldKey=name";
    private final TrueTabsRepository trueTabsRepository;

    @Override
    public List<Supply> execute() {
        ApiResponse<DataWrapper<Supply>> response = trueTabsRepository.get(PATH, new ParameterizedTypeReference<ApiResponse<DataWrapper<Supply>>>() {
        }).block();
        System.out.println(response);

        if (response != null) {
            log.info("code = {}, success = {}, message = {}", response.getCode(), response.isSuccess(), response.getMessage());
            return response.getData().getRecords().stream().map(RecordsWrapper::getFields).toList();
        }
        else {
            throw new SupplyException("Response is null");
        }
    }
}
