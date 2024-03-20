package t.it.simplegraphql.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;
import t.it.simplegraphql.dto.PongResponse;
import t.it.simplegraphql.services.SimpleService;

@GraphQlTest(SimpleController.class)
class SimpleControllerTest {
    @Autowired
    private GraphQlTester graphQlTester;

    @MockBean
    private SimpleService simpleService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void ping_ShouldSuccess() throws Exception {
        Mockito.when(simpleService.ping()).thenReturn(new PongResponse("pong"));

        final var dummyPongResponse = objectMapper.writeValueAsString(new PongResponse("pong"));

        graphQlTester.documentName("pingPong")
                .execute()
                .path("response")
                .matchesJson(dummyPongResponse);
    }
}