package t.it.restclient.applications.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import t.it.restclient.domains.entities.Product;
import t.it.restclient.domains.entities.ProductDetail;
import t.it.restclient.domains.repositories.ProductRepository;
import t.it.restclient.interfaces.models.requests.AddProduct;
import t.it.restclient.interfaces.models.responses.PersistedProduct;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Mono<PersistedProduct> addProduct(Mono<AddProduct> addProduct) {
        return productRepository.addProduct(
                addProduct.map(item ->
                        Product.builder()
                                .name(item.name())
                                .productDetail(ProductDetail.builder()
                                        .price(item.price())
                                        .capacity(item.capacity())
                                        .color(item.color())
                                        .year(item.year())
                                        .build())
                                .build())
        ).map(product -> PersistedProduct.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getProductDetail().getPrice())
                .createdAt(product.getCreatedAt())
                .build());
    }

    @Override
    public Mono<List<PersistedProduct>> getProducts() {
        return productRepository.products().map(products -> products.stream().map(product -> PersistedProduct.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .price(Optional.ofNullable(product.getProductDetail())
                                .map(ProductDetail::getPrice)
                                .orElse(null))
                        .build()
                ).toList()
        );
    }
}
