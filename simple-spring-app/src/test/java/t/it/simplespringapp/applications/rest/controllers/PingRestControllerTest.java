package t.it.simplespringapp.applications.rest.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import t.it.simplespringapp.applications.models.responses.PongResponse;
import t.it.simplespringapp.domains.services.PingService;

@DirtiesContext
@WebFluxTest
@ExtendWith(SpringExtension.class)
class PingRestControllerTest {
    @MockBean
    private PingService pingService;

    @Autowired
    private WebTestClient webClient;

    @DirtiesContext
    @Test
    void ping_ShouldSuccess() throws Exception {
        Mockito.when(pingService.ping()).thenReturn(Mono.just("pong"));


        webClient.get().uri("/ping").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk().expectBody(PongResponse.class).consumeWith(pongResponseEntityExchangeResult ->
                {
                    PongResponse responseBody = pongResponseEntityExchangeResult.getResponseBody();
                    Assertions.assertNotNull(responseBody);
                    Assertions.assertEquals("pong", responseBody.message());
                }
        );
    }
}