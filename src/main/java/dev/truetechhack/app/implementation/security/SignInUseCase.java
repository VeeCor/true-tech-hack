package dev.truetechhack.app.implementation.security;

import dev.truetechhack.app.api.TrueTabsRepository;
import dev.truetechhack.app.api.security.SignInInbound;
import dev.truetechhack.app.exception.ProductException;
import dev.truetechhack.domain.response.ApiResponse;
import dev.truetechhack.domain.response.DataWrapper;
import dev.truetechhack.domain.response.RecordsWrapper;
import dev.truetechhack.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignInUseCase implements SignInInbound {
    private static final String PATH = "/dstlr5pDegVHcXYZWA/records?viewId=viwXKQJReqX5j&fieldKey=name";
    private final TrueTabsRepository trueTabsRepository;

    @Override
    public void execute(String email, String password) {
        trueTabsRepository.get(PATH,
                new ParameterizedTypeReference<ApiResponse<DataWrapper<User>>>() {})
            .flatMap(response -> {
                log.info("code = {}, success = {}, message = {}", response.getCode(), response.isSuccess(), response.getMessage());
                return Mono.justOrEmpty(
                    response.getData().getRecords().stream()
                        .map(RecordsWrapper::getFields)
                        .filter(user -> email.equals(user.getEmail()) && password.equals(user.getPassword()))
                        .findFirst()
                );
            })
            .switchIfEmpty(Mono.error(new ProductException("Invalid email or password")))
            .doOnNext(user -> log.info("User authenticated: {}", user))
            .subscribe();
    }
}
