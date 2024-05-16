package t.it.simplespringconfig.commons.configs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpringPropertiesTest {
    private SpringProperties springProperties;

    @BeforeEach
    void setUp() {
        springProperties = new SpringProperties();
        SpringProperties.ApplicationProperties applicationProperties = new SpringProperties.ApplicationProperties();
        applicationProperties.setName("simple-spring-config");

        SpringProperties.DatasourceProperties dataSourceProperties = new SpringProperties.DatasourceProperties();
        dataSourceProperties.setUrl("jdbc:db//191.8.1.1:3360/test");
        dataSourceProperties.setUsername("root");
        dataSourceProperties.setPassword("root");

        springProperties.setApplication(applicationProperties);
        springProperties.setDatasource(dataSourceProperties);
    }

    @Test
    void testInit() {
        assertNotNull(springProperties);
    }

    @Test
    void testConfig() {
        assertEquals("simple-spring-config", springProperties.getApplication().getName());
        assertEquals("jdbc:db//191.8.1.1:3360/test", springProperties.getDatasource().getUrl());
        assertEquals("root", springProperties.getDatasource().getUsername());
        assertEquals("root", springProperties.getDatasource().getPassword());
    }
}
