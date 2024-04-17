package t.it.simplespringlogging.infrastructure.repositories;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import t.it.simplespringlogging.domain.repositories.GreetingRepository;

@Slf4j
@Repository
public class GreetingRepositoryImpl implements GreetingRepository {
    @Override
    public Mono<String> getGreeting(String name) {
        log.info("greeting {}", name);
        return Mono.just("Hi " + name + '!');
    }
}
