package t.it.simplespringdatabase.domains.repositories;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import t.it.simplespringdatabase.domains.repositories.entities.Product;

public interface ProductRepository {
    Mono<Product> addProduct(Product product);
    Flux<Product> findProducts();
    Mono<Product> updateProduct(Product product);
    Mono<Void> deleteProduct(Product product);
    Mono<Product> findProductById(String id);
}
