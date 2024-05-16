package t.it.simplespringconfig.commons.configs;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class SimpleAppProperties {
    @Value("${APP_PORT}")
    private int port;
    @Value("${APP_NAME}")
    private String name;
    @Value("${DB_HOST}")
    private String dbHost;
    @Value("${DB_USERNAME}")
    private String dbUsername;
    @Value("${DB_PASSWORD}")
    private String dbPassword;

    @PostConstruct
    public void init() {
        System.out.println("APP_NAME\t: " + port);
        System.out.println("APP_NAME\t: " + name);
        System.out.println("DB_HOST\t: " + dbHost);
        System.out.println("DB_USERNAME\t: " + dbUsername);
        System.out.println("DB_PASSWORD\t: " + dbPassword);
    }
}
