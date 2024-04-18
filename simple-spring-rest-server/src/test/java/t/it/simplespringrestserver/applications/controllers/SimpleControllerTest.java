package t.it.simplespringrestserver.applications.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;
import t.it.simplespringrestserver.applications.models.requests.AddUser;
import t.it.simplespringrestserver.applications.models.requests.UpdateUser;
import t.it.simplespringrestserver.applications.models.responses.CurrentUserState;
import t.it.simplespringrestserver.applications.models.responses.WebResponse;
import t.it.simplespringrestserver.domains.services.SimpleService;

import java.time.Instant;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@WebFluxTest
@ExtendWith(SpringExtension.class)
class SimpleControllerTest {
    @MockBean
    private SimpleService simpleService;

    @Autowired
    private WebTestClient webClient;

    @Test
    void testGreeting_Success() {
        final String dummyName = "cisnux";

        when(simpleService.getGreetingByName(dummyName)).thenReturn(Mono.just("Hi " + dummyName + "!"));

        webClient.get().uri("/hello/" + dummyName).accept(MediaType.APPLICATION_JSON).exchange()
                .expectStatus().isOk()
                .expectBody(WebResponse.class).value(webResponse -> {
                    assertEquals("Hi " + dummyName + "!", webResponse.data());
                    assertEquals(String.valueOf(HttpStatus.OK.value()), webResponse.meta().code());
                });

        verify(simpleService, times(1)).getGreetingByName(dummyName);
    }

    @Test
    void testAddUser_Success() {
        AddUser addUser = AddUser.builder().name("cisnux").age(20).build();
        CurrentUserState currentUserState = CurrentUserState.builder()
                .id(UUID.randomUUID().toString())
                .message("Hello, " + addUser.name() + " you are " + addUser.age() + " years old")
                .timestamp(Instant.now().getEpochSecond())
                .build();

        when(simpleService.addUser(any(AddUser.class))).thenReturn(Mono.just(currentUserState));

        webClient.post().uri("/hello").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(addUser)).exchange().expectStatus().isCreated().expectBody(new ParameterizedTypeReference<WebResponse<CurrentUserState>>() {
        }).value(webResponse -> {
            CurrentUserState actualData = webResponse.data();
            assertEquals(currentUserState.id(), actualData.id());
            assertEquals(currentUserState.message(), actualData.message());
            assertEquals(String.valueOf(HttpStatus.CREATED.value()), webResponse.meta().code());
        });

        verify(simpleService, times(1)).addUser(any(AddUser.class));
    }

    @Test
    void testUpdateUser_Success() {
        UpdateUser updateUser = UpdateUser.builder().currentName("cisnux").newName("fajra").build();

        CurrentUserState currentUserState = CurrentUserState.builder()
                .id(UUID.randomUUID().toString())
                .message("Your name is replaced from " + updateUser.currentName() + " to " + updateUser.newName())
                .timestamp(Instant.now().getEpochSecond())
                .build();

        when(simpleService.updateAllFields(any(UpdateUser.class))).thenReturn(Mono.just(currentUserState));

        webClient.put().uri("/hello").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(updateUser)).exchange().expectStatus().isOk().expectBody(new ParameterizedTypeReference<WebResponse<CurrentUserState>>() {
        }).value(webResponse -> {
            CurrentUserState actualData = webResponse.data();
            assertEquals(currentUserState.id(), actualData.id());
            assertEquals(currentUserState.message(), actualData.message());
            assertEquals(String.valueOf(HttpStatus.OK.value()), webResponse.meta().code());
        });

        verify(simpleService, times(1)).updateAllFields(any(UpdateUser.class));
    }

    @Test
    void testUpdateUserById_Success() {
        UpdateUser updateUser = UpdateUser.builder().newName("fajra").build();

        CurrentUserState currentUserState = CurrentUserState.builder()
                .id(UUID.randomUUID().toString())
                .message("Your name is replaced to " + updateUser.newName())
                .timestamp(Instant.now().getEpochSecond())
                .build();

        when(simpleService.updateSomeFields(any(UpdateUser.class))).thenReturn(Mono.just(currentUserState));

        webClient.patch().uri("/hello/" + currentUserState.id()).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(updateUser)).exchange().expectStatus().isOk().expectBody(new ParameterizedTypeReference<WebResponse<CurrentUserState>>() {
        }).value(webResponse -> {
            CurrentUserState actualData = webResponse.data();
            assertEquals(currentUserState.id(), actualData.id());
            assertEquals(currentUserState.message(), actualData.message());
            assertEquals(String.valueOf(HttpStatus.OK.value()), webResponse.meta().code());
        });

        verify(simpleService, times(1)).updateSomeFields(any(UpdateUser.class));
    }

    @Test
    void testDeleteUserById_Success() {
        CurrentUserState currentUserState = CurrentUserState.builder()
                .id(UUID.randomUUID().toString())
                .message("Your data is deleted")
                .timestamp(Instant.now().getEpochSecond())
                .build();

        when(simpleService.deleteUser(anyString())).thenReturn(Mono.just(currentUserState));

        webClient.delete().uri("/hello/" + currentUserState.id()).accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk().expectBody(new ParameterizedTypeReference<WebResponse<CurrentUserState>>() {
        }).value(webResponse -> {
            CurrentUserState actualData = webResponse.data();
            assertEquals(currentUserState.id(), actualData.id());
            assertEquals(currentUserState.message(), actualData.message());
            assertEquals(String.valueOf(HttpStatus.OK.value()), webResponse.meta().code());
        });

        verify(simpleService, times(1)).deleteUser(anyString());
    }
}