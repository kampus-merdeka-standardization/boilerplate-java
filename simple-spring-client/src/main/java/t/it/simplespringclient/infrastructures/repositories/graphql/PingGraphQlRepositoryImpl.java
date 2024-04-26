package t.it.simplespringclient.infrastructures.repositories.graphql;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.client.FieldAccessException;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import t.it.simplespringclient.domains.repositories.PingRepository;

import java.util.Collections;

@RequiredArgsConstructor
@Repository
public class PingGraphQlRepositoryImpl implements PingRepository {
    private final HttpGraphQlClient httpGraphQlClient;

    @Override
    public Flux<String> ping() {
        return httpGraphQlClient.documentName("pingPong")
                .retrieve("message")
                .toEntity(String.class)
                .flatMapIterable(Collections::singleton);
    }
}
