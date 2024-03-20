package t.it.simplerest.webmvc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import t.it.simplerest.webmvc.dto.PongResponse;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SimpleControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void ping_ShouldSuccess() throws Exception {
        final var dummyPongResponse = objectMapper.writeValueAsString(new PongResponse("pong"));

        mockMvc.perform(
                get("/ping")
        ).andExpectAll(
                status().isOk(),
                content().json(dummyPongResponse)
        );
    }
}