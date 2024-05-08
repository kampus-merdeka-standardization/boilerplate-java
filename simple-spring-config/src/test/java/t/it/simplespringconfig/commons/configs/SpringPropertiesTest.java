package t.it.simplespringconfig.commons.configs;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

class SpringPropertiesTest {
    @SpringBootTest
    @Nested
    @ActiveProfiles("dev")
    class DevConfigTest{
        @Autowired
        private SpringProperties springProperties;

        @Test
        void testConfig() {
            assertEquals("simple-spring-config", springProperties.getApplication().getName());
            assertEquals("jdbc:db://localhost:3306/examples", springProperties.getDatasource().getUrl());
            assertEquals("root", springProperties.getDatasource().getUsername());
            assertEquals("root", springProperties.getDatasource().getPassword());
        }
    }

    @SpringBootTest
    @Nested
    @ActiveProfiles("prod")
    class ProdConfigTest{
        @Autowired
        private SpringProperties springProperties;

        @Test
        void testConfig() {
            assertEquals("simple-spring-config", springProperties.getApplication().getName());
            assertEquals("jdbc:db://191.20.10.10:3306/examples", springProperties.getDatasource().getUrl());
            assertEquals("developer", springProperties.getDatasource().getUsername());
            assertEquals("developer123", springProperties.getDatasource().getPassword());
        }
    }
 }
