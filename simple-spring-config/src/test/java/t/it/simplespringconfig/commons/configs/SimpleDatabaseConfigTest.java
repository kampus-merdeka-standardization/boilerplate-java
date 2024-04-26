package t.it.simplespringconfig.commons.configs;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

class SimpleDatabaseConfigTest {
    @SpringBootTest
    @Nested
    @ActiveProfiles("dev")
    class DevConfigTest{
        @Autowired
        private SimpleDatabaseConfig simpleDatabaseConfig;

        @Test
        void testConfig() {
            assertEquals("root", simpleDatabaseConfig.getUsername());
            assertEquals("123", simpleDatabaseConfig.getPassword());
            assertEquals("jdbc:db://localhost:3306/examples", simpleDatabaseConfig.getUrl());
        }
    }

    @SpringBootTest
    @Nested
    @ActiveProfiles("prod")
    class ProdConfigTest{
        @Autowired
        private SimpleDatabaseConfig simpleDatabaseConfig;

        @Test
        void testConfig() {
            assertEquals("cisnux", simpleDatabaseConfig.getUsername());
            assertEquals("cisnux123", simpleDatabaseConfig.getPassword());
            assertEquals("jdbc:db://191.20.10.10:3306/examples", simpleDatabaseConfig.getUrl());
        }
    }
}