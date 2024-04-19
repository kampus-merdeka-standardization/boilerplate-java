package t.it.simplespringclient.domains.services;

import reactor.core.publisher.Mono;
import t.it.simplespringclient.domains.services.models.AddProduct;
import t.it.simplespringclient.domains.services.models.UpdateProduct;
import t.it.simplespringclient.applications.models.responses.PersistedProductDetailResponse;
import t.it.simplespringclient.applications.models.responses.PersistedProductResponse;

import java.util.List;

public interface ProductService {
    Mono<PersistedProductResponse> addProduct(Mono<AddProduct> addProduct);
    Mono<List<PersistedProductResponse>> getProducts();
    Mono<PersistedProductResponse> updateSomeProductFields(Mono<UpdateProduct> updateProduct);
    Mono<PersistedProductResponse> updateAllProductFields(Mono<UpdateProduct> updateProduct);
    Mono<String> deleteProductById(String id);
    Mono<PersistedProductDetailResponse> getProductById(String id);
}
