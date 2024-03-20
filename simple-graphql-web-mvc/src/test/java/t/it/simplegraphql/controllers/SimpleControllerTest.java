package t.it.simplegraphql.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import t.it.simplegraphql.dto.PongResponse;

@GraphQlTest(SimpleController.class)
class SimpleControllerTest {
    @Autowired
    private GraphQlTester graphQlTester;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void ping_ShouldSuccess() throws Exception {
        final var dummyPongResponse = objectMapper.writeValueAsString(new PongResponse("pong"));

        graphQlTester.document("ping")
                .execute()
                .path("response")
                .matchesJson(dummyPongResponse);
    }
}