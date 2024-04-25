package t.it.simplespringapp.applications.rest.controllers;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import t.it.simplespringapp.applications.responses.WebResponse;
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
    void ping_Success() {
        when(pingService.ping()).thenReturn(Mono.just("pong"));

        webClient.get().uri("/ping").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk().expectBody(WebResponse.class).consumeWith(pongResponseEntityExchangeResult -> {
            WebResponse<?> responseBody = pongResponseEntityExchangeResult.getResponseBody();
            assertNotNull(responseBody);
            assertEquals(String.valueOf(HttpStatus.OK.value()),responseBody.meta().code());
            assertEquals("pong", responseBody.data());
        });

        verify(pingService, times(1)).ping();
    }

    @DirtiesContext
    @Test
    void ping_Fail(){
        when(pingService.ping()).thenThrow(RuntimeException.class);

        webClient.get().uri("/ping").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().is5xxServerError();

        verify(pingService, times(1)).ping();
    }
}