package t.it.simplespringclient.infrastructures.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import t.it.simplespringclient.domains.repositories.PingRepository;
import t.it.simplespringclient.applications.models.responses.PongResponse;

@RequiredArgsConstructor
@Repository
public class PingGraphQlRepositoryImpl implements PingRepository {
    private final HttpGraphQlClient httpGraphQlClient;

    @Override
    public Mono<String> ping() {
        return httpGraphQlClient.documentName("pingPong")
                .retrieve("pingPong")
                .toEntity(PongResponse.class).map(PongResponse::message);
    }
}
