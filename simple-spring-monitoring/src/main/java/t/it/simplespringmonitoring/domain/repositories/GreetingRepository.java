package t.it.simplespringmonitoring.domain.repositories;

import reactor.core.publisher.Mono;

public interface GreetingRepository {
    Mono<String> getGreeting(final String name);
}
