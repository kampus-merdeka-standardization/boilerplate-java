package t.it.simplespringconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import t.it.simplespringconfig.commons.configs.SimpleDatabaseConfig;

@SpringBootApplication
@EnableConfigurationProperties({
        SimpleDatabaseConfig.class
})
public class SimpleSpringConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleSpringConfigApplication.class, args);
    }
}
