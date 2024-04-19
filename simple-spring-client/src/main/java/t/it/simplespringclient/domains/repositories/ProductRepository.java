package t.it.simplespringclient.domains.repositories;

import reactor.core.publisher.Mono;
import t.it.simplespringclient.domains.repositories.entities.Product;

import java.util.List;

public interface ProductRepository {
    Mono<Product> addProduct(Mono<Product> product);

    Mono<List<Product>> products();

    Mono<Product> updateAllProductFields(Mono<Product> product);

    Mono<Product> updateSomeProductFields(Mono<Product> product);

    Mono<String> deleteProductById(String id);
    Mono<Product> findProductById(String id);
}
