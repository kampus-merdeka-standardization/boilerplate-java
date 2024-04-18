package t.it.simplespringapp.applications.graphql.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.annotation.DirtiesContext;
import reactor.core.publisher.Mono;
import t.it.simplespringapp.domains.services.PingService;
import t.it.simplespringapp.infrastructures.config.AppConfig;
import t.it.simplespringapp.applications.models.responses.PongResponse;


@DirtiesContext
@GraphQlTest(value = PingGraphQlController.class)
// we need to import explicitly the config if we use custom scalar type
@Import(AppConfig.class)
class PingGraphQlControllerTest {
    @Autowired
    private GraphQlTester graphQlTester;

    @MockBean
    private PingService pingService;

    @DirtiesContext
    @Test
    void ping_ShouldSuccess() {
        Mockito.when(pingService.ping()).thenReturn(Mono.just("pong"));

        graphQlTester.documentName("pingPong")
                .execute()
                .path("response")
                .entity(PongResponse.class)
                .matches(pongResponse -> pongResponse.equals(PongResponse.builder().message("pong").build()));

        Mockito.verify(pingService, Mockito.times(1)).ping();
    }
}