package t.it.simplespringrestserver.domains.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import t.it.simplespringrestserver.applications.models.requests.AddUser;
import t.it.simplespringrestserver.applications.models.responses.CurrentUserState;

import static org.junit.jupiter.api.Assertions.*;

class SimpleServiceTest {
    private SimpleService simpleService;

    @BeforeEach
    void setUp() {
        simpleService = new SimpleServiceImpl();
    }

    @Test
    void testInit() {
        assertNotNull(simpleService);
    }

    @Test
    void testGetGreeting() {
        final var dummyName = "cisnux";
        Mono<String> greeting = simpleService.getGreetingByName(dummyName);

        StepVerifier.create(greeting).expectNext("Hello, " + dummyName + "!")
                .verifyComplete();
    }

    @Test
    void testAddUser() {
        final AddUser dummyAddUser = AddUser.builder().name("cisnux").age(20).build();
        Mono<CurrentUserState> currentUserState = simpleService.addUser(dummyAddUser);

        StepVerifier.create(currentUserState).assertNext(response -> {
            assertEquals("Hello, " + dummyAddUser.name() + " you are " + dummyAddUser.age() + " years old", response.message());
            assertNotNull(response.message());
        }).verifyComplete();
    }
}