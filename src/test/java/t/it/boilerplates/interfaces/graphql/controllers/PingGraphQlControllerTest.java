package t.it.boilerplates.interfaces.graphql.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.annotation.DirtiesContext;
import t.it.boilerplates.commons.config.AppConfig;
import t.it.boilerplates.interfaces.models.responses.PongResponse;
import t.it.boilerplates.applications.services.PingService;


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
        Mockito.when(pingService.ping()).thenReturn(PongResponse.builder().message("pong").build());

        graphQlTester.documentName("pingPong")
                .execute()
                .path("response")
                .entity(PongResponse.class)
                .matches(pongResponse -> pongResponse.equals(PongResponse.builder().message("pong").build()));

        Mockito.verify(pingService, Mockito.times(1)).ping();
    }
}