package t.it.restclient.applications.services;

import reactor.core.publisher.Mono;
import t.it.restclient.domains.entities.Product;
import t.it.restclient.interfaces.models.requests.AddProduct;
import t.it.restclient.interfaces.models.responses.PersistedProduct;

import java.util.List;

public interface ProductService {
    Mono<PersistedProduct> addProduct(AddProduct addProduct);
    Mono<List<PersistedProduct>> getProducts();
}
