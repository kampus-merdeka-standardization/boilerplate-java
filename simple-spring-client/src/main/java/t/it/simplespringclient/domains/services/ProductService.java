package t.it.simplespringclient.domains.services;

import reactor.core.publisher.Mono;
import t.it.simplespringclient.domains.services.models.AddProduct;
import t.it.simplespringclient.domains.services.models.UpdateProduct;
import t.it.simplespringclient.domains.services.models.PersistedProductDetail;
import t.it.simplespringclient.domains.services.models.PersistedProduct;

import java.util.List;

public interface ProductService {
    Mono<PersistedProduct> addProduct(Mono<AddProduct> addProduct);
    Mono<List<PersistedProduct>> getProducts();
    Mono<PersistedProduct> updateSomeProductFields(Mono<UpdateProduct> updateProduct);
    Mono<PersistedProduct> updateAllProductFields(Mono<UpdateProduct> updateProduct);
    Mono<String> deleteProductById(String id);
    Mono<PersistedProductDetail> getProductById(String id);
}
