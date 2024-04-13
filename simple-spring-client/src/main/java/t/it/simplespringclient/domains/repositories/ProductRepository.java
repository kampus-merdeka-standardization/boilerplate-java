package t.it.simplespringclient.domains.repositories;

import reactor.core.publisher.Mono;
import t.it.simplespringclient.domains.entities.Product;


import java.util.List;

public interface ProductRepository {
    Mono<Product> addProduct(Mono<Product> product);

    Mono<List<Product>> products();
}
