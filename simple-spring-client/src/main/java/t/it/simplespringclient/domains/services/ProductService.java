package t.it.simplespringclient.domains.services;

import reactor.core.publisher.Mono;
import t.it.simplespringclient.applications.models.requests.AddProduct;
import t.it.simplespringclient.applications.models.responses.PersistedProductResponse;

import java.util.List;

public interface ProductService {
    Mono<PersistedProductResponse> addProduct(Mono<AddProduct> addProduct);
    Mono<List<PersistedProductResponse>> getProducts();
}
