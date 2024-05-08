package t.it.simplespringconfig.commons.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "server")
@Getter
@Setter
public class ServerProperties {
    private int port;
}
