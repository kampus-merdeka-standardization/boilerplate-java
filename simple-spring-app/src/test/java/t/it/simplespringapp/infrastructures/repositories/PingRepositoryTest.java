package t.it.simplespringapp.infrastructures.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import t.it.simplespringapp.domains.repositories.PingRepository;

import static org.junit.jupiter.api.Assertions.*;

@DirtiesContext
@SpringBootTest
class PingRepositoryTest {
    @Autowired
    private PingRepository pingRepository;

    @DirtiesContext
    @Test
    void getPing_ShouldSuccess() {
        final var expectedOutput = "pong";

        assertEquals(expectedOutput, pingRepository.getPong());
    }
}