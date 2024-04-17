package t.it.simplespringlogging.domain.services;

import reactor.core.publisher.Mono;

public interface GreetingService {
    Mono<String> doGreeting(final String name);
}
