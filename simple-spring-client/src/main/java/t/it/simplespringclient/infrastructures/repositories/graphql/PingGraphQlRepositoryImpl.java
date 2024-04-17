package t.it.simplespringclient.infrastructures.repositories.graphql;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import t.it.simplespringclient.domains.repositories.PingRepository;
import t.it.simplespringclient.applications.models.responses.PongResponse;

import java.util.Collections;

@RequiredArgsConstructor
@Repository
public class PingGraphQlRepositoryImpl implements PingRepository {
    private final HttpGraphQlClient httpGraphQlClient;

    @Override
    public Flux<String> ping() {
        return httpGraphQlClient.documentName("pingPong")
                .retrieve("pingPong")
                .toEntity(PongResponse.class).map(PongResponse::message).flatMapIterable(Collections::singleton);
    }
}
