package t.it.simplespringapp.applications.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import t.it.simplespringapp.commons.exceptions.NotFoundResourceException;
import t.it.simplespringapp.domains.entities.Product;
import t.it.simplespringapp.domains.repositories.ProductRepository;
import t.it.simplespringapp.interfaces.models.requests.AddProductRequest;
import t.it.simplespringapp.interfaces.models.requests.UpdateAllProductFieldRequest;
import t.it.simplespringapp.interfaces.models.requests.UpdateSomeProductFieldsRequest;
import t.it.simplespringapp.interfaces.models.responses.AddedProductResponse;
import t.it.simplespringapp.interfaces.models.responses.ProductDetailResponse;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public AddedProductResponse addProduct(AddProductRequest addProductRequest) {
        final Product product = Product.builder().name(addProductRequest.name()).price(addProductRequest.price()).createdAt(LocalDateTime.ofInstant(Instant.ofEpochMilli(addProductRequest.createdAt()), ZoneOffset.UTC)).updatedAt(LocalDateTime.ofInstant(Instant.ofEpochMilli(addProductRequest.updatedAt()), ZoneOffset.UTC)).description(addProductRequest.description()).category(addProductRequest.category()).build();
        String generatedId = productRepository.addNewProduct(product);

        return AddedProductResponse.builder().id(generatedId).name(product.getName()).price(addProductRequest.price()).category(addProductRequest.category()).build();
    }

    @Override
    public AddedProductResponse updateProduct(UpdateAllProductFieldRequest updateAllProductField) {
        final var product = productRepository.findById(updateAllProductField.id()).orElseThrow(() -> new NotFoundResourceException("product is not found"));

        product.setName(updateAllProductField.name());
        product.setPrice(updateAllProductField.price());
        product.setDescription(updateAllProductField.description());
        product.setCategory(updateAllProductField.category());
        product.setUpdatedAt(LocalDateTime.ofInstant(Instant.ofEpochMilli(updateAllProductField.updatedAt()), ZoneOffset.UTC));

        productRepository.updateProduct(product);

        return AddedProductResponse.builder().id(product.getId()).name(product.getName()).price(product.getPrice()).category(product.getCategory()).build();
    }

    @Override
    public AddedProductResponse updateProduct(UpdateSomeProductFieldsRequest updateSomeProductFields) {
        final var product = productRepository.findById(updateSomeProductFields.id()).orElseThrow(() -> new NotFoundResourceException("product is not found"));
        log.info("fields: {}", updateSomeProductFields);

        Optional.ofNullable(product).ifPresent(item -> {
            log.info("before update product : {}", product);
            Optional.ofNullable(updateSomeProductFields.name()).ifPresent(name -> {
                if (!name.isBlank()) item.setName(name);
            });
            Optional.ofNullable(updateSomeProductFields.category()).ifPresent(category -> {
                if (!category.isBlank()) item.setCategory(category);
            });
            Optional.ofNullable(updateSomeProductFields.price()).ifPresent(price -> {
                if (price > 0L) item.setPrice(price);
            });
            Optional.ofNullable(updateSomeProductFields.description()).ifPresent(description -> {
                if (!description.isBlank()) item.setDescription(description);
            });
            Optional.ofNullable(updateSomeProductFields.updatedAt()).ifPresent(updatedAt -> {
                if (updatedAt > 0L)
                    item.setUpdatedAt(LocalDateTime.ofInstant(Instant.ofEpochMilli(updatedAt), ZoneOffset.UTC));
            });
            log.info("after update product : {}", product);
        });

        productRepository.updateProduct(product);

        return AddedProductResponse.builder().id(product.getId()).name(product.getName()).price(product.getPrice()).category(product.getCategory()).build();
    }

    @Override
    public ProductDetailResponse getProductById(String id) {
        final var product = productRepository.findById(id).orElseThrow(() -> new NotFoundResourceException("product is not found"));
        return ProductDetailResponse.builder().id(product.getId()).name(product.getName()).price(product.getPrice()).createdAt(product.getCreatedAt().toEpochSecond(ZoneOffset.UTC)).updatedAt(product.getUpdatedAt().toEpochSecond(ZoneOffset.UTC)).description(product.getDescription()).category(product.getCategory()).build();
    }

    @Override
    public List<AddedProductResponse> getProducts() {
        return productRepository.getProducts().stream().map(product -> AddedProductResponse.builder().id(product.getId()).name(product.getName()).price(product.getPrice()).category(product.getCategory()).build()).toList();
    }

    @Override
    public void removeProductById(String id) {
        final var product = productRepository.findById(id).orElseThrow(() -> new NotFoundResourceException("product is not found"));
        productRepository.deleteProduct(product);
    }
}

