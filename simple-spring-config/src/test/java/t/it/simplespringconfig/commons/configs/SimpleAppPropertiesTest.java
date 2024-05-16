package t.it.simplespringconfig.commons.configs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class SimpleAppPropertiesTest {
    private SimpleAppProperties simpleAppProperties;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        simpleAppProperties = new SimpleAppProperties();
        final var simpleAppPropertiesClass = simpleAppProperties.getClass();

        Field name = simpleAppPropertiesClass.getDeclaredField("name");
        name.setAccessible(true);
        name.set(simpleAppProperties, "simple-spring-config");

        Field port = simpleAppPropertiesClass.getDeclaredField("port");
        port.setAccessible(true);
        port.set(simpleAppProperties, 9111);

        Field dbHost = simpleAppPropertiesClass.getDeclaredField("dbHost");
        dbHost.setAccessible(true);
        dbHost.set(simpleAppProperties, "localhost:3306");

        Field dbUsername = simpleAppPropertiesClass.getDeclaredField("dbUsername");
        dbUsername.setAccessible(true);
        dbUsername.set(simpleAppProperties, "root");

        Field dbPassword = simpleAppPropertiesClass.getDeclaredField("dbPassword");
        dbPassword.setAccessible(true);
        dbPassword.set(simpleAppProperties, "root");
    }

    @Test
    void testInit() {
        assertNotNull(simpleAppProperties);
    }

    @Test
    void testConfig() {
        assertEquals("simple-spring-config", simpleAppProperties.getName());
        assertEquals(9111, simpleAppProperties.getPort());
        assertEquals("localhost:3306", simpleAppProperties.getDbHost());
        assertEquals("root", simpleAppProperties.getDbUsername());
        assertEquals("root", simpleAppProperties.getDbPassword());
    }
}