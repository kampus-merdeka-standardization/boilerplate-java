package t.it.simplespringapp.applications.services;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import t.it.simplespringapp.interfaces.models.requests.AddProductRequest;
import t.it.simplespringapp.interfaces.models.requests.UpdateAllProductFieldRequest;
import t.it.simplespringapp.interfaces.models.requests.UpdateSomeProductFieldsRequest;
import t.it.simplespringapp.interfaces.models.responses.AddedProductResponse;
import t.it.simplespringapp.interfaces.models.responses.ProductDetailResponse;

import java.util.List;

@Validated
public interface ProductService {
    AddedProductResponse addProduct(@Valid AddProductRequest addProductRequest);

    AddedProductResponse updateProduct(@Valid UpdateAllProductFieldRequest updateAllProductField);
    AddedProductResponse updateProduct(@Valid UpdateSomeProductFieldsRequest updateSomeProductFields);

    ProductDetailResponse getProductById(@NotBlank String id);

    List<AddedProductResponse> getProducts();

    void removeProductById(@NotBlank String id);
}
