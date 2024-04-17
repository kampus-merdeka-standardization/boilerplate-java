package t.it.simplespringclient.domains.repositories;

import reactor.core.publisher.Flux;

public interface PingRepository {
    Flux<String> ping();
}
