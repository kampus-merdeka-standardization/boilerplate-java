package t.it.simplespringapp.domains.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


@DirtiesContext
@SpringBootTest
class PingServiceTest {
    @Autowired
    private PingService pingService;

    @DirtiesContext
    @Test
    void ping_ShouldSuccess() {
        Mono<String> ping = pingService.ping();
        StepVerifier.create(ping)
                .expectNext("pong")
                .verifyComplete();
    }
}