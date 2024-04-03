package t.it.restclient.interfaces.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import t.it.restclient.applications.services.ProductService;
import t.it.restclient.interfaces.models.requests.AddProduct;
import t.it.restclient.interfaces.models.responses.PersistedProduct;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping(path = "/products")
    public Mono<PersistedProduct> addProduct(@RequestBody Mono<AddProduct> addProduct) {
        return productService.addProduct(addProduct);
    }

    @GetMapping(path = "/products")
    public Mono<List<PersistedProduct>> getProducts(){
        return productService.getProducts();
    }
}
