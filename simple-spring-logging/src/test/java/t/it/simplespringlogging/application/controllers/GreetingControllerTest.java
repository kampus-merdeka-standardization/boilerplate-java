package t.it.simplespringlogging.application.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import t.it.simplespringlogging.application.responses.WebResponse;
import t.it.simplespringlogging.domain.services.GreetingService;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@DirtiesContext
@WebFluxTest
@ExtendWith(SpringExtension.class)
class GreetingControllerTest {
    @MockBean
    private GreetingService greetingService;

    @Autowired
    private WebTestClient webClient;

    @Test
    void testGreeting_Success() {
        var dummyName = "test";
        var dummyGreeting = "Hello " + dummyName + '!';

        when(greetingService.doGreeting(dummyName)).thenReturn(Mono.just(dummyGreeting));

        webClient.get().uri("/greeting/" + dummyName).exchange().expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<WebResponse<String>>() {
                }).value(webResponse -> {
                    assertEquals(dummyGreeting, webResponse.data());
                    assertEquals(String.valueOf(HttpStatus.OK.value()), webResponse.meta().code());
                });

        verify(greetingService, times(1)).doGreeting(dummyName);
    }

    @Test
    void testGreeting_Fail() {
        var dummyName = "test";

        when(greetingService.doGreeting(dummyName)).thenThrow(RuntimeException.class);

        webClient.get().uri("/greeting/" + dummyName).exchange().expectStatus().is5xxServerError();

        verify(greetingService, times(1)).doGreeting(dummyName);
    }
}