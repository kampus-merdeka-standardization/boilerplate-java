package t.it.boilerplates.interfaces.graphql.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.annotation.DirtiesContext;
import t.it.boilerplates.interfaces.models.responses.PongResponse;
import t.it.boilerplates.applications.services.PingService;


@DirtiesContext
@GraphQlTest(PingGraphQlController.class)
class PingGraphQlControllerTest {
    @Autowired
    private GraphQlTester graphQlTester;

    @MockBean
    private PingService pingService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void ping_ShouldSuccess() throws Exception {
        Mockito.when(pingService.ping()).thenReturn(PongResponse.builder().message("pong").build());

        final var expectedPongJson = objectMapper.writeValueAsString(PongResponse.builder().message("pong").build());

        graphQlTester.documentName("pingPong")
                .execute()
                .path("response")
                .matchesJson(expectedPongJson);
    }
}