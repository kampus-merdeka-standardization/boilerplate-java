package t.it.simplespringdatabase.domains.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import t.it.simplespringdatabase.domains.services.models.*;

public interface ProductService {
    Mono<PersistedProduct> addProduct(Mono<AddProduct> addProduct);
    Flux<PersistedProduct> getProducts();
    Mono<PersistedProduct> updateSomeProductFields(Mono<UpdateSomeProduct> updateProduct);
    Mono<PersistedProduct> updateAllProductFields(Mono<UpdateAllProduct> updateProduct);
    Mono<String> deleteProductById(String id);
    Mono<PersistedProductDetail> getProductById(String id);
}
