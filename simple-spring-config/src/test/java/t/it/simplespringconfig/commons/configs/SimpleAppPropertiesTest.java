package t.it.simplespringconfig.commons.configs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SimpleAppPropertiesTest {
    @Mock
    private SimpleAppProperties simpleAppProperties;

    @Test
    void testInit() {
        assertNotNull(simpleAppProperties);
    }

    @Test
    void testConfig() {
        when(simpleAppProperties.getName()).thenReturn("simple-spring-config");
        when(simpleAppProperties.getPort()).thenReturn(9111);
        when(simpleAppProperties.getDbHost()).thenReturn("localhost:3306");
        when(simpleAppProperties.getDbUsername()).thenReturn("root");
        when(simpleAppProperties.getDbPassword()).thenReturn("root");

        assertEquals("simple-spring-config", simpleAppProperties.getName());
        assertEquals(9111, simpleAppProperties.getPort());
        assertEquals("localhost:3306", simpleAppProperties.getDbHost());
        assertEquals("root", simpleAppProperties.getDbUsername());
        assertEquals("root", simpleAppProperties.getDbPassword());
    }
}