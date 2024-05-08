package t.it.simplespringconfig.commons.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring")
@Getter
@Setter
public class SpringProperties {
    private ApplicationProperties application;
    private DatasourceProperties datasource;

    @Getter
    @Setter
    public static class ApplicationProperties{
        private String name;
    }

    @Getter
    @Setter
    public static class DatasourceProperties {
        private String username;
        private String password;
        private String url;
    }
}
