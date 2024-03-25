package t.it.boilerplates.applications.services;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import t.it.boilerplates.PongResponse;
import t.it.boilerplates.domains.entities.Pong;
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
        Mockito.when(pingRepository.getPong()).thenReturn(Pong.builder().message("pong").build());

        final var expectedPongResponse = PongResponse.newBuilder().setMessage("pong").build();

        assertEquals(expectedPongResponse, pingService.ping());
    }

    @Test
    void ping_ShouldFail() {
        Mockito.when(pingRepository.getPong()).thenThrow(new RuntimeException("invalid ping"));

        assertThrows(RuntimeException.class, () -> pingService.ping().getMessage());
    }
}