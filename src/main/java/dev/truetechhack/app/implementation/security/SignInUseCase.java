package dev.truetechhack.app.implementation.security;

import dev.truetechhack.app.api.TrueTabsRepository;
import dev.truetechhack.app.api.security.SignInInbound;
import dev.truetechhack.app.exception.ProductException;
import dev.truetechhack.domain.response.ApiResponse;
import dev.truetechhack.domain.response.DataWrapper;
import dev.truetechhack.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignInUseCase implements SignInInbound {
    private static final String PATH = "/dstlr5pDegVHcXYZWA/records?";
    private final TrueTabsRepository trueTabsRepository;

    @Override
    public void execute(String email, String password) {
        String uri = UriComponentsBuilder.fromPath(PATH)
            .queryParam("email", email)
            .queryParam("password", password)
            .build()
            .toUriString();

        trueTabsRepository.get(uri,
                new ParameterizedTypeReference<ApiResponse<DataWrapper<User>>>() {
                }).doOnNext(response -> {
                log.info("code = {}, success = {}, message = {}", response.getCode(), response.isSuccess(), response.getMessage());
            }).doOnError(throwable -> {
                throw new ProductException(throwable.getMessage());
            }).subscribe();
    }
}
