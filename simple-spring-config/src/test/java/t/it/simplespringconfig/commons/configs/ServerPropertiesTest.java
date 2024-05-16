package t.it.simplespringconfig.commons.configs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServerPropertiesTest {
    private ServerProperties serverProperties;

    @BeforeEach
    void setUp() {
        serverProperties = new ServerProperties();
        serverProperties.setPort(5050);
    }

    @Test
    void testInit() {
        assertNotNull(serverProperties);
    }

    @Test
    void testConfig() {
        assertEquals(5050, serverProperties.getPort());
    }
}