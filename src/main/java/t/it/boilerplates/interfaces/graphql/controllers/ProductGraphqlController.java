package t.it.boilerplates.interfaces.graphql.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import t.it.boilerplates.applications.services.ProductService;
import t.it.boilerplates.interfaces.models.requests.AddProductRequest;
import t.it.boilerplates.interfaces.models.requests.UpdateAllProductFieldRequest;
import t.it.boilerplates.interfaces.models.requests.UpdateSomeProductFieldsRequest;
import t.it.boilerplates.interfaces.models.responses.AddedProductResponse;
import t.it.boilerplates.interfaces.models.responses.ProductDetailResponse;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ProductGraphqlController {
    private final ProductService productService;

    @MutationMapping
    public AddedProductResponse addProduct(@Argument AddProductRequest addProductRequest) {
        return productService.addProduct(addProductRequest);
    }

    @QueryMapping
    public List<AddedProductResponse> findAllProducts(){
        return productService.getProducts();
    }

    @QueryMapping
    public ProductDetailResponse getProductById(@Argument String id){
        return productService.getProductById(id);
    }

    @MutationMapping
    public AddedProductResponse updateSomeProductFieldsById(@Argument String id, @Argument UpdateSomeProductFieldsRequest updateSomeProductFieldsRequest){
        return productService.updateProduct(updateSomeProductFieldsRequest.withId(id));
    }

    @MutationMapping
    public AddedProductResponse updateAllProductFieldsById(@Argument String id, @Argument UpdateAllProductFieldRequest updateAllProductFieldsRequest){
        return productService.updateProduct(updateAllProductFieldsRequest.withId(id));
    }

    @MutationMapping
    public String deleteProductById(@Argument String id){
        productService.removeProductById(id);
        return "OK";
    }
}
