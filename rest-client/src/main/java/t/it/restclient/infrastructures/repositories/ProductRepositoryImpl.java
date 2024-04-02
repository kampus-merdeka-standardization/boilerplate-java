package t.it.restclient.infrastructures.repositories;

import lombok.RequiredArgsConstructor;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import t.it.restclient.domains.entities.Product;
import t.it.restclient.domains.repositories.ProductRepository;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    private final WebClient webClient;

    @Override
    public Mono<Product> addProduct(Product product) {
        Mono<Product> productMono = Mono.just(product);
        return webClient.post()
                .uri("/objects")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(productMono, Product.class)
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
