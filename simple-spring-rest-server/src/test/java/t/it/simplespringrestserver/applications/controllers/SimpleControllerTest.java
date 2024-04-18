package t.it.simplespringrestserver.applications.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import t.it.simplespringrestserver.applications.models.responses.WebResponse;
import t.it.simplespringrestserver.domains.services.SimpleService;

import static org.mockito.Mockito.*;

@WebFluxTest
@ExtendWith(SpringExtension.class)
class SimpleControllerTest {
    @MockBean
    private SimpleService simpleService;

    @Autowired
    private WebTestClient webClient;

    @Test
    void testGreeting() {
        final String dummyName = "cisnux";

        when(simpleService.getGreetingByName(dummyName)).thenReturn(Mono.just("Hi " + dummyName + "!"));

        webClient.get().uri("/hello/" + dummyName).accept(MediaType.APPLICATION_JSON).exchange()
                .expectStatus().isOk()
                .expectBody(WebResponse.class).value(webResponse -> Assertions.assertEquals("Hi " + dummyName + "!", webResponse.data()));

        verify(simpleService, times(1)).getGreetingByName(dummyName);
    }
}