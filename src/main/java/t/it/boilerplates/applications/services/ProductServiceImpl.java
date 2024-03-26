package t.it.boilerplates.applications.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import t.it.boilerplates.domains.entities.Product;
import t.it.boilerplates.domains.repositories.ProductRepository;
import t.it.boilerplates.interfaces.models.requests.AddProductRequest;
import t.it.boilerplates.interfaces.models.requests.UpdateAllProductFieldRequest;
import t.it.boilerplates.interfaces.models.requests.UpdateSomeProductFieldsRequest;
import t.it.boilerplates.interfaces.models.responses.AddedProductResponse;
import t.it.boilerplates.interfaces.models.responses.ProductDetailResponse;

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
        final Product product = Product.builder()
                .name(addProductRequest.name())
                .price(addProductRequest.price())
                .createdAt(LocalDateTime.ofInstant(Instant.ofEpochMilli(addProductRequest.createdAt()), ZoneOffset.UTC))
                .updatedAt(LocalDateTime.ofInstant(Instant.ofEpochMilli(addProductRequest.updatedAt()), ZoneOffset.UTC))
                .description(addProductRequest.description())
                .category(addProductRequest.category())
                .build();
        String generatedId = productRepository.addNewProduct(product);

        return AddedProductResponse.builder()
                .id(generatedId)
                .name(product.getName())
                .price(addProductRequest.price())
                .category(addProductRequest.category())
                .build();
    }

    @Override
    public AddedProductResponse updateProduct(UpdateAllProductFieldRequest updateAllProductField) {
        final var product = productRepository.findById(updateAllProductField.id()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product is not found"));

        product.setName(updateAllProductField.name());
        product.setPrice(updateAllProductField.price());
        product.setDescription(updateAllProductField.description());
        product.setCategory(updateAllProductField.category());
        product.setUpdatedAt(LocalDateTime.ofInstant(Instant.ofEpochMilli(updateAllProductField.updatedAt()), ZoneOffset.UTC));

        productRepository.updateProduct(product);

        return AddedProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .category(product.getCategory())
                .build();
    }

    @Override
    public AddedProductResponse updateProduct(UpdateSomeProductFieldsRequest updateSomeProductFields) {
        final var product = productRepository.findById(updateSomeProductFields.id()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product is not found"));
        log.info("fields: {}", updateSomeProductFields);

        Optional.ofNullable(product).ifPresent(item -> {
            log.info("before update product : {}", product);
            Optional.ofNullable(updateSomeProductFields.name()).ifPresent(item::setName);
            Optional.ofNullable(updateSomeProductFields.category()).ifPresent(item::setCategory);
            Optional.ofNullable(updateSomeProductFields.price()).ifPresent(item::setPrice);
            Optional.ofNullable(updateSomeProductFields.description()).ifPresent(item::setDescription);
            Optional.ofNullable(updateSomeProductFields.updatedAt()).ifPresent(updatedAt -> item.setUpdatedAt(LocalDateTime.ofInstant(Instant.ofEpochMilli(updatedAt), ZoneOffset.UTC)));
            log.info("after update product : {}", product);
        });

        productRepository.updateProduct(product);

        return AddedProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .category(product.getCategory())
                .build();
    }

    @Override
    public ProductDetailResponse getProductById(String id) {
        final var product = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product is not found"));
        return ProductDetailResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .createdAt(product.getCreatedAt().toEpochSecond(ZoneOffset.UTC))
                .updatedAt(product.getUpdatedAt().toEpochSecond(ZoneOffset.UTC))
                .description(product.getDescription())
                .category(product.getCategory())
                .build();
    }

    @Override
    public List<AddedProductResponse> getProducts() {
        return productRepository.getProducts().stream().map(product -> AddedProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .category(product.getCategory())
                .build()).toList();
    }

    @Override
    public void removeProductById(String id) {
        final var product = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product is not found"));
        productRepository.deleteProduct(product);
    }
}

