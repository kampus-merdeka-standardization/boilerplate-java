package t.it.simplespringconfig.commons.configs;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

class ServerPropertiesTest {
    @SpringBootTest
    @Nested
    @ActiveProfiles("dev")
    class DevConfigTest {
        @Autowired
        private ServerProperties serverProperties;

        @Test
        void testConfig() {
            assertEquals(5120, serverProperties.getPort());
        }
    }

    @SpringBootTest
    @Nested
    @ActiveProfiles("prod")
    class ProdConfigTest {
        @Autowired
        private ServerProperties serverProperties;

        @Test
        void testConfig() {
            assertEquals(9999, serverProperties.getPort());
        }
    }
}