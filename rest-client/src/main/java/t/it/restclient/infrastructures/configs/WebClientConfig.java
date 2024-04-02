package t.it.restclient.infrastructures.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    private static final String BASE_URL = "https://api.restful-api.dev";

    @Bean
    WebClient webClient(){
        return WebClient.builder()
                .baseUrl(WebClientConfig.BASE_URL)
                .build();
    }
}
