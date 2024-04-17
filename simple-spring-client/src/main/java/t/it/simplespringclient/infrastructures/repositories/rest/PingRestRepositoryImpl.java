package t.it.simplespringclient.infrastructures.repositories.rest;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import t.it.simplespringclient.applications.models.responses.PongResponse;
import t.it.simplespringclient.domains.repositories.PingRepository;

@Repository
public class PingRestRepositoryImpl implements PingRepository {
    private final WebClient webClient;

    public PingRestRepositoryImpl(@Qualifier("pingRestWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<String> ping() {
        return webClient.get()
                .uri("/ping")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(PongResponse.class).map(PongResponse::message);
    }
}
