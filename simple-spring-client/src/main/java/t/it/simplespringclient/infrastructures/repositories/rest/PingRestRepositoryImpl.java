package t.it.simplespringclient.infrastructures.repositories.rest;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import t.it.simplespringclient.domains.repositories.PingRepository;
import t.it.simplespringclient.infrastructures.repositories.responses.WebResponse;

import java.util.Collections;

@Repository
public class PingRestRepositoryImpl implements PingRepository {
    private final WebClient webClient;

    public PingRestRepositoryImpl(@Qualifier("pingRestWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Flux<String> ping() {
        return webClient.get()
                .uri("/ping")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::isError, ClientResponse::createException)
                .bodyToMono(new ParameterizedTypeReference<WebResponse<String>>() {
                })
                .map(WebResponse::data).flatMapIterable(Collections::singleton);
    }
}
