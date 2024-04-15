package t.it.simplespringapp.applications.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import t.it.simplespringapp.applications.models.responses.PongResponse;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext
@SpringBootTest
@AutoConfigureMockMvc
class PingRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @DirtiesContext
    @Test
    void ping_ShouldSuccess() throws Exception {
        final var expectedPongJson = objectMapper.writeValueAsString(PongResponse.builder().message("pong").build());

        mockMvc.perform(
                get("/ping")
        ).andExpectAll(
                status().isOk(),
                content().json(expectedPongJson)
        );
    }
}