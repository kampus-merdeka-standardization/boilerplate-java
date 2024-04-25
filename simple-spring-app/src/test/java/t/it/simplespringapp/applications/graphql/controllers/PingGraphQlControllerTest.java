package t.it.simplespringapp.applications.graphql.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.annotation.DirtiesContext;
import reactor.core.publisher.Mono;
import t.it.simplespringapp.domains.services.PingService;
import t.it.simplespringapp.commons.config.AppConfig;


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
    void ping_Success() {
        Mockito.when(pingService.ping()).thenReturn(Mono.just("pong"));

        graphQlTester.documentName("pingPong")
                .execute()
                .path("message")
                .entity(String.class)
                .matches(pongResponse -> pongResponse.equals("pong"));

        Mockito.verify(pingService, Mockito.times(1)).ping();
    }

    @DirtiesContext
    @Test
    void ping_Fail() {
        Mockito.when(pingService.ping()).thenThrow(RuntimeException.class);

        graphQlTester.documentName("pingPong")
                .execute()
                .errors()
                .expect(responseError -> ErrorType.INTERNAL_ERROR.equals(responseError.getErrorType()));

        Mockito.verify(pingService, Mockito.times(1)).ping();
    }
}