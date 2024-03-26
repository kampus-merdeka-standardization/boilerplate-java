package t.it.boilerplates.applications.services;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import t.it.boilerplates.interfaces.models.responses.PongResponse;
import t.it.boilerplates.domains.repositories.PingRepository;

import static org.junit.jupiter.api.Assertions.*;

@DirtiesContext
@SpringBootTest
class PingServiceTest {
    @MockBean
    private PingRepository pingRepository;

    @Autowired
    private PingService pingService;

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