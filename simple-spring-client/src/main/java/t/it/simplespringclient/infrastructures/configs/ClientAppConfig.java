package t.it.simplespringclient.infrastructures.configs;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.SslProvider;
import t.it.simplespringclient.commons.Constants;
import t.it.simplespringclient.services.PingServiceGrpc;

import javax.net.ssl.KeyManagerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.UUID;

@Configuration
@Slf4j
public class ClientAppConfig {
    @Value("${RESTFUL_API_KEYSTORE}")
    private String keyStorePath;
    @Value("${RESTFUL_API_KEYSTORE_PASSWORD}")
    private String keyStorePassword;
    @GrpcClient("ping")
    private PingServiceGrpc.PingServiceStub pingServiceStub;

    @Profile({"production"})
    @Bean("productWebClient")
    WebClient sslProductWebClient(SslContext sslContext) {
        log.info("production web client");
        SslProvider sslProvider = SslProvider.builder().sslContext(sslContext).build();
        HttpClient httpClient = HttpClient.create().secure(sslProvider);

        return WebClient.builder().baseUrl(Constants.PRODUCT_BASE_URL)
                .defaultHeader("X-API-TOKEN", UUID.randomUUID().toString())
                .clientConnector(new ReactorClientHttpConnector(httpClient)).build();
    }

    @Profile({"local"})
    @Bean("productWebClient")
    WebClient nonSslProductWebClient() {
        log.info("local web client");
        return WebClient.builder().baseUrl(Constants.PRODUCT_BASE_URL)
                .defaultHeader("X-API-TOKEN", UUID.randomUUID().toString())
                .build();
    }

    @Bean
    SslContext sslContext() throws KeyStoreException, CertificateException, IOException, NoSuchAlgorithmException, UnrecoverableKeyException {
        @Cleanup InputStream keyStoreFileInputStream = Files.newInputStream(Path.of(keyStorePath));
        KeyStore keyStore = KeyStore.getInstance("jks");
        keyStore.load(keyStoreFileInputStream, keyStorePassword.toCharArray());
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
        keyManagerFactory.init(keyStore, keyStorePassword.toCharArray());

        return SslContextBuilder.forClient().keyManager(keyManagerFactory).build();
    }

    @Bean
    public WebClient pingGraphQlWebClient() {
        return WebClient.builder().baseUrl(Constants.PING_BASE_URL + "/graphql")
                .build();
    }

    @Bean
    public WebClient pingRestWebClient() {
        return WebClient.builder().baseUrl(Constants.PING_BASE_URL)
                .build();
    }

    @Bean
    HttpGraphQlClient httpGraphQlClient(@Qualifier("pingGraphQlWebClient") WebClient webClient) {
        return HttpGraphQlClient.create(webClient);
    }

    @Bean
    PingServiceGrpc.PingServiceStub pingServiceStub() {
        return pingServiceStub;
    }
}
