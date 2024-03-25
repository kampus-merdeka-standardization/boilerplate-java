package t.it.boilerplates.interfaces.graphql.controllers;

import com.google.protobuf.util.JsonFormat;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.annotation.DirtiesContext;
import t.it.boilerplates.PongResponse;
import t.it.boilerplates.applications.services.PingService;


@DirtiesContext
@GraphQlTest(PingGraphQlController.class)
class PingGraphQlControllerTest {
    @Autowired
    private GraphQlTester graphQlTester;

    @MockBean
    private PingService pingService;

    @Test
    void ping_ShouldSuccess() throws Exception {
        Mockito.when(pingService.ping()).thenReturn(PongResponse.newBuilder().setMessage("pong").build());

        final var expectedPongJson = JsonFormat.printer().print(PongResponse.newBuilder().setMessage("pong").build());

        graphQlTester.documentName("pingPong")
                .execute()
                .path("response")
                .matchesJson(expectedPongJson);
    }
}