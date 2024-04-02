package t.it.simplespringapp.interfaces.rest.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import t.it.simplespringapp.applications.services.ProductService;
import t.it.simplespringapp.interfaces.models.requests.AddProductRequest;
import t.it.simplespringapp.interfaces.models.requests.UpdateAllProductFieldRequest;
import t.it.simplespringapp.interfaces.models.requests.UpdateSomeProductFieldsRequest;
import t.it.simplespringapp.interfaces.models.responses.AddedProductResponse;
import t.it.simplespringapp.interfaces.models.responses.ProductDetailResponse;
import t.it.simplespringapp.interfaces.models.responses.WebResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductRestController {
    private final ProductService productService;

    @PostMapping(path = "/products", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public WebResponse<AddedProductResponse> addProduct(@RequestBody AddProductRequest addProductRequest) {
        final var addedProduct = productService.addProduct(addProductRequest);

        return WebResponse.<AddedProductResponse>builder().data(addedProduct).build();
    }

    @GetMapping(path = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public WebResponse<List<AddedProductResponse>> getProducts() {
        return WebResponse.<List<AddedProductResponse>>builder().data(productService.getProducts()).build();
    }

    @GetMapping(path = "/products/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public WebResponse<ProductDetailResponse> getProductById(@PathVariable("productId") String productId) {
        return WebResponse.<ProductDetailResponse>builder().data(productService.getProductById(productId)).build();
    }

    @PatchMapping(path = "/products/{productId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public WebResponse<AddedProductResponse> updateSomeProductFieldsById(@PathVariable("productId") String productId, @RequestBody UpdateSomeProductFieldsRequest updateSomeProductFieldsRequest) {
        return WebResponse.<AddedProductResponse>builder().data(productService.updateProduct(updateSomeProductFieldsRequest.withId(productId))).build();
    }

    @PutMapping(path = "/products/{productId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public WebResponse<AddedProductResponse> updateAllProductFieldsById(@PathVariable("productId") String productId, @RequestBody UpdateAllProductFieldRequest updateAllProductFieldRequest) {
        return WebResponse.<AddedProductResponse>builder().data(productService.updateProduct(updateAllProductFieldRequest.withId(productId))).build();
    }

    @DeleteMapping(path = "/products/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public WebResponse<String> deleteProductById(@PathVariable("productId") String productId) {
        productService.removeProductById(productId);
        return WebResponse.<String>builder().data("OK").build();
    }
}
