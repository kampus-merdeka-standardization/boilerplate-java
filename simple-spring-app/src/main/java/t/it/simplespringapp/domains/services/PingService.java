package t.it.simplespringapp.domains.services;

import reactor.core.publisher.Mono;

public interface PingService {
    Mono<String> ping();
}
