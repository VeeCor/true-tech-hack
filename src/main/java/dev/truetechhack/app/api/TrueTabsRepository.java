package dev.truetechhack.app.api;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;

public interface TrueTabsRepository {
    <T> Mono<T> get(String path, ParameterizedTypeReference<T> responseType);

    <T, R> Mono<R> post(String path, T body, Class<R> responseType);

    Mono<String> postFile(String path, MultipartFile file) throws IOException;

    <T, R> Mono<R> put(String path, T body, Class<R> responseType);

    <R> Mono<R> delete(String path, Class<R> responseType);
}
