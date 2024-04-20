package t.it.simplespringlogging.domain.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import t.it.simplespringlogging.domain.repositories.GreetingRepository;

import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
class GreetingServiceImplTest {
    @Mock
    private GreetingRepository greetingRepository;

    private GreetingService greetingService;

    @BeforeEach
    void setUp() {
        greetingService = new GreetingServiceImpl(greetingRepository);
    }

    @Test
    void testDoGreeting() {
        var dummyName = "test";
        var dummyGreeting = "Hello " + dummyName + '!';

        when(greetingRepository.getGreeting(dummyName)).thenReturn(Mono.just(dummyGreeting));

        Mono<String> greeting = greetingService.doGreeting(dummyName);

        StepVerifier.create(greeting)
                .expectNext(dummyGreeting)
                .verifyComplete();

        verify(greetingRepository, times(1)).getGreeting(dummyName);
    }
}