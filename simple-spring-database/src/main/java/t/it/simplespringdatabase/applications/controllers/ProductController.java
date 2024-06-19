package t.it.simplespringdatabase.applications.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import t.it.simplespringdatabase.applications.payloads.responses.MetaResponse;
import t.it.simplespringdatabase.applications.payloads.responses.WebResponse;
import t.it.simplespringdatabase.domains.services.ProductService;
import t.it.simplespringdatabase.domains.services.models.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping(path = "/products", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<WebResponse<PersistedProduct>> addProduct(@RequestBody Mono<AddProduct> addProduct) {
        return productService.addProduct(addProduct).map(persistedProduct ->
                WebResponse.<PersistedProduct>builder()
                        .meta(MetaResponse.builder().code(String.valueOf(HttpStatus.CREATED.value())).message(HttpStatus.CREATED.getReasonPhrase()).build()).data(persistedProduct).build());
    }

    @GetMapping(path = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<WebResponse<List<PersistedProduct>>> getProducts() {
        return productService.getProducts()
                .collectList()
                .map(
                        persistedProducts -> WebResponse.<List<PersistedProduct>>builder()
                                .data(persistedProducts)
                                .meta(MetaResponse.builder().code(String.valueOf(HttpStatus.OK.value())).message(HttpStatus.OK.getReasonPhrase()).build())
                                .build()
                )
                .subscribeOn(Schedulers.parallel());
    }

    @GetMapping(path = "products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<WebResponse<PersistedProductDetail>> getProduct(@PathVariable("id") String id) {
        return productService.getProductById(id).map(productDetailResponse -> WebResponse.<PersistedProductDetail>builder()
                .data(productDetailResponse)
                .meta(MetaResponse.builder().code(String.valueOf(HttpStatus.OK.value())).message(HttpStatus.OK.getReasonPhrase()).build())
                .build());
    }

    @PutMapping(path = "/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<WebResponse<PersistedProduct>> updateAllProductFields(@PathVariable("id") String id, @RequestBody Mono<UpdateAllProduct> updateProduct) {
        return productService.updateAllProductFields(updateProduct.map(item -> item.withId(id))).map(persistedProductResponses -> WebResponse.<PersistedProduct>builder()
                .data(persistedProductResponses)
                .meta(MetaResponse.builder().code(String.valueOf(HttpStatus.OK.value())).message(HttpStatus.OK.getReasonPhrase()).build())
                .build());
    }

    @PatchMapping(path = "/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<WebResponse<PersistedProduct>> updateSomeProductFields(@PathVariable("id") String id, @RequestBody Mono<UpdateSomeProduct> updateProduct) {
        return productService.updateSomeProductFields(updateProduct.map(item -> item.withId(id))).map(persistedProductResponses -> WebResponse.<PersistedProduct>builder()
                .data(persistedProductResponses)
                .meta(MetaResponse.builder().code(String.valueOf(HttpStatus.OK.value())).message(HttpStatus.OK.getReasonPhrase()).build())
                .build());
    }

    @DeleteMapping(path = "products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<WebResponse<String>> deleteProductById(@PathVariable("id") String id) {
        return productService.deleteProductById(id).map(deletedId -> WebResponse.<String>builder()
                .data(deletedId)
                .meta(MetaResponse.builder().code(String.valueOf(HttpStatus.OK.value())).message(HttpStatus.OK.getReasonPhrase()).build())
                .build());
    }
}
