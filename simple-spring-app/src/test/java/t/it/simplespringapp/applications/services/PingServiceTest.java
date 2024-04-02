package t.it.simplespringapp.applications.services;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import t.it.simplespringapp.domains.repositories.PingRepository;
import t.it.simplespringapp.interfaces.models.responses.PongResponse;

import static org.junit.jupiter.api.Assertions.*;

@DirtiesContext
@SpringBootTest
class PingServiceTest {
    @MockBean
    private PingRepository pingRepository;

    @Autowired
    private PingService pingService;

    @DirtiesContext
    @Test
    void ping_ShouldSuccess() {
        Mockito.when(pingRepository.getPong()).thenReturn("pong");

        final var expectedPongResponse = PongResponse.builder().message("pong").build();

        assertEquals(expectedPongResponse, pingService.ping());
    }

    @Test
    void ping_ShouldFail() {
        Mockito.when(pingRepository.getPong()).thenThrow(new RuntimeException("invalid ping"));

        assertThrows(RuntimeException.class, () -> pingService.ping());
    }
}