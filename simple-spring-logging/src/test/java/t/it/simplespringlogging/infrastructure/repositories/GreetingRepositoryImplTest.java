package t.it.simplespringlogging.infrastructure.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import t.it.simplespringlogging.domain.repositories.GreetingRepository;

class GreetingRepositoryImplTest {
    private GreetingRepository greetingRepository;

    @BeforeEach
    void setUp() {
        greetingRepository = new GreetingRepositoryImpl();
    }

    @Test
    void testGetGreeting() {
        var dummyName = "test";

        Mono<String> greeting = greetingRepository.getGreeting(dummyName);

        StepVerifier.create(greeting)
                .expectNext("Hi " + dummyName + '!')
                        .verifyComplete();
    }
}