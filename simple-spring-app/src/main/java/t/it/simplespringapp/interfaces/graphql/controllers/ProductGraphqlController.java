package t.it.simplespringapp.interfaces.graphql.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import t.it.simplespringapp.applications.services.ProductService;
import t.it.simplespringapp.interfaces.models.requests.AddProductRequest;
import t.it.simplespringapp.interfaces.models.requests.UpdateAllProductFieldRequest;
import t.it.simplespringapp.interfaces.models.requests.UpdateSomeProductFieldsRequest;
import t.it.simplespringapp.interfaces.models.responses.AddedProductResponse;
import t.it.simplespringapp.interfaces.models.responses.ProductDetailResponse;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ProductGraphqlController {
    private final ProductService productService;

    @MutationMapping
    public AddedProductResponse addProduct(@Argument(name = "addProductRequest") AddProductRequest addProductRequest) {
        return productService.addProduct(addProductRequest);
    }

    @QueryMapping
    public List<AddedProductResponse> findAllProducts(){
        return productService.getProducts();
    }

    @QueryMapping
    public ProductDetailResponse getProductById(@Argument(name = "id") String id){
        return productService.getProductById(id);
    }

    @MutationMapping
    public AddedProductResponse updateSomeProductFieldsById(@Argument(name = "id") String id, @Argument(name = "updateSomeProductFieldsRequest") UpdateSomeProductFieldsRequest updateSomeProductFieldsRequest){
        return productService.updateProduct(updateSomeProductFieldsRequest.withId(id));
    }

    @MutationMapping
    public AddedProductResponse updateAllProductFieldsById(@Argument(name = "id") String id, @Argument(name = "updateAllProductFieldsRequest") UpdateAllProductFieldRequest updateAllProductFieldsRequest){
        return productService.updateProduct(updateAllProductFieldsRequest.withId(id));
    }

    @MutationMapping
    public String deleteProductById(@Argument(name = "id") String id){
        productService.removeProductById(id);
        return "OK";
    }
}
