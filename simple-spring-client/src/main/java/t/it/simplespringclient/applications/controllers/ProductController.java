package t.it.simplespringclient.applications.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import t.it.simplespringclient.applications.models.responses.WebResponse;
import t.it.simplespringclient.domains.services.ProductService;
import t.it.simplespringclient.applications.models.requests.AddProduct;
import t.it.simplespringclient.applications.models.responses.PersistedProductResponse;
import t.it.simplespringclient.infrastructures.repositories.payloads.responses.MetaResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping(path = "/products")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<WebResponse<PersistedProductResponse>> addProduct(@RequestBody Mono<AddProduct> addProduct) {
        return productService.addProduct(addProduct).map(persistedProductResponse ->
        {
            return WebResponse.<PersistedProductResponse>builder()
                    .meta(MetaResponse.builder().code(String.valueOf(HttpStatus.CREATED.value())).message(HttpStatus.CREATED.getReasonPhrase()).build()).data(persistedProductResponse).build();
        });
    }

    @GetMapping(path = "/products")
    public Mono<WebResponse<List<PersistedProductResponse>>> getProducts() {
        return productService.getProducts().map(persistedProductResponses -> WebResponse.<List<PersistedProductResponse>>builder()
                .data(persistedProductResponses)
                .meta(MetaResponse.builder().code(String.valueOf(HttpStatus.OK.value())).message(HttpStatus.OK.getReasonPhrase()).build())
                .build());
    }
}
