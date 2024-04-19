package t.it.simplespringclient.infrastructures.repositories.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import t.it.simplespringclient.domains.repositories.entities.Product;
import t.it.simplespringclient.domains.repositories.ProductRepository;
import t.it.simplespringclient.infrastructures.repositories.payloads.responses.MetaResponse;

import java.util.List;

@Slf4j
@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final WebClient webClient;

    public ProductRepositoryImpl(@Qualifier("productWebClient") WebClient webClient) {
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

    @Override
    public Mono<Product> updateAllProductFields(Mono<Product> product) {
        return product.flatMap(item -> webClient.put()
                .uri("/objects/" + item.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(item), Product.class)
                .retrieve()
                .bodyToMono(Product.class));
    }

    @Override
    public Mono<Product> updateSomeProductFields(Mono<Product> product) {
        return product.flatMap(item -> webClient.put()
                .uri("/objects/" + item.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(item), Product.class)
                .retrieve()
                .bodyToMono(Product.class));
    }

    @Override
    public Mono<String> deleteProductById(String id) {
        return webClient.delete()
                .uri("/objects/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(MetaResponse.class)
                .map(MetaResponse::message);
    }

    @Override
    public Mono<Product> findProductById(String id) {
        return webClient.get()
                .uri("/objects/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Product.class);
    }
}
