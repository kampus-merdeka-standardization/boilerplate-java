package t.it.boilerplates.interfaces.rest.controllers;

import com.google.protobuf.util.JsonFormat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import t.it.boilerplates.PongResponse;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PingRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void ping_ShouldSuccess() throws Exception {
        final var expectedPongJson = JsonFormat.printer().print(PongResponse.newBuilder().setMessage("pong").build());

        mockMvc.perform(
                get("/ping")
        ).andExpectAll(
                status().isOk(),
                content().json(expectedPongJson)
        );
    }
}