package t.it.simplespringconfig.commons.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.datasource")
@Getter
@Setter
public class SimpleDatabaseConfig {
    private String username;
    private String password;
    private String url;
}
