package t.it.restclient.domains.repositories;

import reactor.core.publisher.Mono;
import t.it.restclient.domains.entities.Product;

import java.util.List;

public interface ProductRepository {
    Mono<Product> addProduct(Mono<Product> product);

    Mono<List<Product>> products();
}
