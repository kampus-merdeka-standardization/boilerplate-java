package t.it.boilerplates.domains.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@DirtiesContext
@SpringBootTest
class PingRepositoryTest {
    @Autowired
    private PingRepository pingRepository;

    @Test
    void getPing_ShouldSuccess() {
        final var expectedOutput = "pong";

        assertEquals(expectedOutput, pingRepository.getPong());
    }
}