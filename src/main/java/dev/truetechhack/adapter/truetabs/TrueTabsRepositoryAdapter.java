package dev.truetechhack.adapter.truetabs;

import dev.truetechhack.app.api.TrueTabsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class TrueTabsRepositoryAdapter implements TrueTabsRepository {
    private final WebClient webClient;

    @Override
    public <T> Mono<T> get(String path, ParameterizedTypeReference<T> responseType) {
        return webClient.get()
            .uri(path)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(responseType);
    }

    @Override
    public <T, R> Mono<R> post(String path, T body, Class<R> responseType) {
        return webClient.post()
            .uri(path)
            .bodyValue(body)
            .retrieve()
            .bodyToMono(responseType);
    }

    @Override
    public Mono<String> postFile(String path, MultipartFile file) throws IOException {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

        InputStreamResource resource = new InputStreamResource(file.getInputStream());

        body.add("file", resource);

        return webClient.post()
            .uri(path)
            .contentType(MediaType.MULTIPART_FORM_DATA)
            .body(BodyInserters.fromMultipartData(body))
            .retrieve()
            .bodyToMono(String.class);
    }

    @Override
    public <T, R> Mono<R> put(String path, T body, Class<R> responseType) {
        return webClient.put()
            .uri(path)
            .bodyValue(body)
            .retrieve()
            .bodyToMono(responseType);
    }

    @Override
    public <R> Mono<R> delete(String path, Class<R> responseType) {
        return webClient.delete()
            .uri(path)
            .retrieve()
            .bodyToMono(responseType);
    }
}
