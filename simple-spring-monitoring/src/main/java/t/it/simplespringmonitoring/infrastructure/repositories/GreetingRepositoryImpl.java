package t.it.simplespringmonitoring.infrastructure.repositories;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import t.it.simplespringmonitoring.domain.repositories.GreetingRepository;

@Slf4j
@Repository
public class GreetingRepositoryImpl implements GreetingRepository {
    @Override
    public Mono<String> getGreeting(String name) {
        log.info("greeting {}", name);
        return Mono.just("Hi " + name + '!');
    }
}
