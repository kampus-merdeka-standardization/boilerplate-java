package t.it.simplerest.webmvc.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PongResponseTest {
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void createPongResponse_ShouldSuccess() throws JsonProcessingException {
        final var actualPongResponse = objectMapper.writeValueAsString(new PongResponse("pong"));

        Assertions.assertTrue(actualPongResponse.contains("message") && actualPongResponse.contains("pong"));
    }
}