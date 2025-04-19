package dev.truetechhack.app.implementation.user;

import dev.truetechhack.app.api.TrueTabsRepository;
import dev.truetechhack.app.api.user.GetAllUserInbound;
import dev.truetechhack.app.exception.UserException;
import dev.truetechhack.domain.response.ApiResponse;
import dev.truetechhack.domain.response.DataWrapper;
import dev.truetechhack.domain.response.RecordsWrapper;
import dev.truetechhack.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetAllUserUseCase implements GetAllUserInbound {
    private static final String PATH = "/dstlr5pDegVHcXYZWA/records?viewId=viwXKQJReqX5j&fieldKey=name";
    private final TrueTabsRepository trueTabsRepository;

    @Override
    public List<User> execute() {
        ApiResponse<DataWrapper<User>> response = trueTabsRepository.get(PATH, new ParameterizedTypeReference<ApiResponse<DataWrapper<User>>>() {
        }).block();

        System.out.println(response);
        if (response != null) {
            log.info("code = {}, success = {}, message = {}", response.getCode(), response.isSuccess(), response.getMessage());
            return response.getData().getRecords().stream().map(RecordsWrapper::getFields).toList();
        }
        else {
            throw new UserException("Response is null");
        }
    }
}
