package t.it.simplespringconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import t.it.simplespringconfig.commons.configs.ServerProperties;
import t.it.simplespringconfig.commons.configs.SpringProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        SpringProperties.class,
        ServerProperties.class
})
public class SimpleSpringConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleSpringConfigApplication.class, args);
    }
}
