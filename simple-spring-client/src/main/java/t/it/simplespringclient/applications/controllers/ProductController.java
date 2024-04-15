package t.it.simplespringclient.applications.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import t.it.simplespringclient.domains.services.ProductService;
import t.it.simplespringclient.applications.models.requests.AddProduct;
import t.it.simplespringclient.applications.models.responses.PersistedProductResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping(path = "/products")
    public Mono<PersistedProductResponse> addProduct(@RequestBody Mono<AddProduct> addProduct) {
        return productService.addProduct(addProduct);
    }

    @GetMapping(path = "/products")
    public Mono<List<PersistedProductResponse>> getProducts(){
        return productService.getProducts();
    }
}
