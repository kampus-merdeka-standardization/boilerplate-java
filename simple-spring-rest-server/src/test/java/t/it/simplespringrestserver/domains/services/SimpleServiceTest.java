package t.it.simplespringrestserver.domains.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import t.it.simplespringrestserver.domains.services.models.AddUser;
import t.it.simplespringrestserver.domains.services.models.CurrentUserState;
import t.it.simplespringrestserver.domains.services.models.UpdateUser;

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

    @Test
    void testUpdateAllFields() {
        final UpdateUser dummyUpdateUser = UpdateUser.builder()
                .id("1")
                .currentName("name")
                .newName("new name")
                .build();

        Mono<CurrentUserState> currentUserState = simpleService.updateAllFields(dummyUpdateUser);

        StepVerifier.create(currentUserState).assertNext(response -> {
            assertEquals("Your name is replaced from " + dummyUpdateUser.currentName() + " to " + dummyUpdateUser.newName(), response.message());
            assertNotNull(response.message());
        }).verifyComplete();
    }

    @Test
    void testUpdateSomeFields() {
        final UpdateUser dummyUpdateUser = UpdateUser.builder()
                .id("1")
                .currentName("name")
                .newName("new name")
                .build();

        Mono<CurrentUserState> currentUserState = simpleService.updateSomeFields(dummyUpdateUser);

        StepVerifier.create(currentUserState).assertNext(response -> {
            assertEquals("Your name is replaced to " + dummyUpdateUser.newName(), response.message());
            assertNotNull(response.message());
        }).verifyComplete();
    }

    @Test
    void testDeleteUser() {
        final String dummyId = "1";

        Mono<CurrentUserState> currentUserState = simpleService.deleteUser(dummyId);

        StepVerifier.create(currentUserState).assertNext(response -> {
            assertEquals("Your data is deleted", response.message());
            assertNotNull(response.message());
        }).verifyComplete();
    }
}