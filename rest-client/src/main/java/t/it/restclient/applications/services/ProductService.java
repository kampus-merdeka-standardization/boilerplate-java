package t.it.restclient.applications.services;

import reactor.core.publisher.Mono;
import t.it.restclient.interfaces.models.requests.AddProduct;
import t.it.restclient.interfaces.models.responses.PersistedProduct;

import java.util.List;

public interface ProductService {
    Mono<PersistedProduct> addProduct(Mono<AddProduct> addProduct);
    Mono<List<PersistedProduct>> getProducts();
}
