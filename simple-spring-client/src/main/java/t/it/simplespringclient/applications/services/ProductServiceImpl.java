package t.it.simplespringclient.applications.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import t.it.simplespringclient.domains.entities.Product;
import t.it.simplespringclient.domains.entities.ProductDetail;
import t.it.simplespringclient.domains.repositories.ProductRepository;
import t.it.simplespringclient.interfaces.models.requests.AddProduct;
import t.it.simplespringclient.interfaces.models.responses.PersistedProduct;

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
                addProduct.map(item -> {
                    var product = new Product();
                    product.setName(item.name());
                    var productDetail = new ProductDetail();
                    productDetail.setPrice(item.price());
                    productDetail.setYear(item.year());
                    productDetail.setColor(item.color());
                    productDetail.setCapacity(item.capacity());
                    product.setProductDetail(productDetail);
                    return product;
                })
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
