package t.it.restclient.infrastructures.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import t.it.restclient.domains.entities.Product;
import t.it.restclient.domains.repositories.ProductRepository;

import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final WebClient webClient;

    public ProductRepositoryImpl(@Qualifier("sslWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<Product> addProduct(Mono<Product> product) {
        return webClient.post()
                .uri("/objects")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(product, Product.class)
                .retrieve()
                .bodyToMono(Product.class);
    }

    @Override
    public Mono<List<Product>> products() {
        return webClient.get()
                .uri("/objects")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(new ParameterizedTypeReference<>() {
                });
    }
}
