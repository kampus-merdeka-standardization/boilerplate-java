package t.it.simplerest.webmvc.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class SimpleRepositoryTest {
    @Autowired
    private SimpleRepository simpleRepository;

    @Test
    void getPing_ShouldSuccess() {
        final var expectedOutput = "pong";

        assertEquals(expectedOutput, simpleRepository.getPong());
    }
}