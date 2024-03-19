package t.it.simplerest.webmvc.services;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import t.it.simplerest.webmvc.dto.PongResponse;
import t.it.simplerest.webmvc.repositories.SimpleRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SimpleServiceTest {
    @MockBean
    private SimpleRepository simpleRepository;

    @Autowired
    private SimpleService simpleService;

    @Test
    void ping_ShouldSuccess() {
        Mockito.when(simpleRepository.getPong()).thenReturn("pong");

        final var expectedPongResponse = new PongResponse("pong");

        assertEquals(expectedPongResponse, simpleService.ping());
    }

    @Test
    void ping_ShouldFail() {
        Mockito.when(simpleRepository.getPong()).thenReturn("not valid");

        final var expectedPongResponse = new PongResponse("pong");

        assertNotEquals(expectedPongResponse, simpleService.ping());
    }
}