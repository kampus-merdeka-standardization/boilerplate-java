package t.it.simplespringclient.applications.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import t.it.simplespringclient.domains.services.models.UpdateProduct;
import t.it.simplespringclient.applications.models.responses.MetaResponse;
import t.it.simplespringclient.applications.models.responses.PersistedProductDetailResponse;
import t.it.simplespringclient.applications.models.responses.WebResponse;
import t.it.simplespringclient.domains.services.ProductService;
import t.it.simplespringclient.domains.services.models.AddProduct;
import t.it.simplespringclient.applications.models.responses.PersistedProductResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping(path = "/products", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<WebResponse<PersistedProductResponse>> addProduct(@RequestBody Mono<AddProduct> addProduct) {
        return productService.addProduct(addProduct).map(persistedProductResponse ->
                WebResponse.<PersistedProductResponse>builder()
                        .meta(MetaResponse.builder().code(String.valueOf(HttpStatus.CREATED.value())).message(HttpStatus.CREATED.getReasonPhrase()).build()).data(persistedProductResponse).build());
    }

    @GetMapping(path = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<WebResponse<List<PersistedProductResponse>>> getProducts() {
        return productService.getProducts().map(persistedProductResponses -> WebResponse.<List<PersistedProductResponse>>builder()
                .data(persistedProductResponses)
                .meta(MetaResponse.builder().code(String.valueOf(HttpStatus.OK.value())).message(HttpStatus.OK.getReasonPhrase()).build())
                .build());
    }

    @GetMapping(path = "products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<WebResponse<PersistedProductDetailResponse>> getProduct(@PathVariable("id") String id) {
        return productService.getProductById(id).map(productDetailResponse -> WebResponse.<PersistedProductDetailResponse>builder()
                .data(productDetailResponse)
                .meta(MetaResponse.builder().code(String.valueOf(HttpStatus.OK.value())).message(HttpStatus.OK.getReasonPhrase()).build())
                .build());
    }

    @PutMapping(path = "/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<WebResponse<PersistedProductResponse>> updateAllProductFields(@PathVariable("id") String id, @RequestBody Mono<UpdateProduct> updateProduct) {
        return productService.updateAllProductFields(updateProduct.map(item -> item.withId(id))).map(persistedProductResponses -> WebResponse.<PersistedProductResponse>builder()
                .data(persistedProductResponses)
                .meta(MetaResponse.builder().code(String.valueOf(HttpStatus.OK.value())).message(HttpStatus.OK.getReasonPhrase()).build())
                .build());
    }

    @PatchMapping(path = "/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<WebResponse<PersistedProductResponse>> updateSomeProductFields(@PathVariable("id") String id, @RequestBody Mono<UpdateProduct> updateProduct) {
        return productService.updateSomeProductFields(updateProduct.map(item -> item.withId(id))).map(persistedProductResponses -> WebResponse.<PersistedProductResponse>builder()
                .data(persistedProductResponses)
                .meta(MetaResponse.builder().code(String.valueOf(HttpStatus.OK.value())).message(HttpStatus.OK.getReasonPhrase()).build())
                .build());
    }

    @DeleteMapping(path = "products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<WebResponse<Void>> deleteProductById(@PathVariable("id") String id) {
        return productService.deleteProductById(id).map(response -> WebResponse.<Void>builder()
                .meta(MetaResponse.builder().code(String.valueOf(HttpStatus.OK.value())).message(response).build())
                .build());
    }
}
