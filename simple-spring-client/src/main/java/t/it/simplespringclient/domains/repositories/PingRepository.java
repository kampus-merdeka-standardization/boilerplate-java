package t.it.simplespringclient.domains.repositories;

import reactor.core.publisher.Mono;

public interface PingRepository {
    Mono<String> ping();
}
