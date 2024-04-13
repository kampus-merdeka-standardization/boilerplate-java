package t.it.simplespringclient.applications.services;

import reactor.core.publisher.Mono;
import t.it.simplespringclient.interfaces.models.requests.AddProduct;
import t.it.simplespringclient.interfaces.models.responses.PersistedProduct;

import java.util.List;

public interface ProductService {
    Mono<PersistedProduct> addProduct(Mono<AddProduct> addProduct);
    Mono<List<PersistedProduct>> getProducts();
}
