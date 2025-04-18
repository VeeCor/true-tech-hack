package dev.truetechhack.app.implementation;

import dev.truetechhack.app.api.TrueTabsRepository;
import dev.truetechhack.domain.response.ApiResponse;
import dev.truetechhack.domain.testEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

//TODO delete (это usecase для примера, по его аналогу будем делать запросы в truetabs)
@Component
@RequiredArgsConstructor
public class testGetUseCase {
    private final TrueTabsRepository apiClient;

    public ApiResponse<List<testEntity>> execute() {
        ApiResponse<List<testEntity>> response = apiClient.get("/dstTBU4JBtwqUhQ6hV/records?viewId=viwi0pFv4bwxj&fieldKey=name", ApiResponse.class)
            .block();
        System.out.println(response);
        return null;
    }
}
