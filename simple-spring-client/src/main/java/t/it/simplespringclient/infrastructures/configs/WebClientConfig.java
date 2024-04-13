package t.it.simplespringclient.infrastructures.configs;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.SslProvider;
import t.it.simplespringclient.infrastructures.commons.Constants;

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
public class WebClientConfig {
    @Value("${RESTFUL_API_KEYSTORE}")
    private String keyStorePath;
    @Value("${RESTFUL_API_KEYSTORE_PASSWORD}")
    private String keyStorePassword;

    @Bean
    WebClient sslWebClient(SslContext sslContext) {
        SslProvider sslProvider = SslProvider.builder().sslContext(sslContext).build();
        HttpClient httpClient = HttpClient.create().secure(sslProvider);

        return WebClient.builder().baseUrl(Constants.BASE_URL)
                .defaultHeader("X-API-TOKEN", UUID.randomUUID().toString())
                .clientConnector(new ReactorClientHttpConnector(httpClient)).build();
    }

    @Bean
    WebClient nonSslWebClient() {
        return WebClient.builder().baseUrl(Constants.BASE_URL)
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
}
