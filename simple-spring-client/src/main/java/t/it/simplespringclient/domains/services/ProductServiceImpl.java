package t.it.simplespringclient.domains.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import t.it.simplespringclient.domains.services.models.UpdateProduct;
import t.it.simplespringclient.applications.models.responses.PersistedProductDetailResponse;
import t.it.simplespringclient.domains.repositories.entities.Product;
import t.it.simplespringclient.domains.repositories.entities.ProductDetail;
import t.it.simplespringclient.domains.repositories.ProductRepository;
import t.it.simplespringclient.domains.services.models.AddProduct;
import t.it.simplespringclient.applications.models.responses.PersistedProductResponse;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Mono<PersistedProductResponse> addProduct(Mono<AddProduct> addProduct) {

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
        ).map(product -> PersistedProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getProductDetail().getPrice())
                .createdAt(product.getCreatedAt())
                .build());
    }

    @Override
    public Mono<List<PersistedProductResponse>> getProducts() {
        return productRepository.products().map(products -> products.stream().map(product -> PersistedProductResponse.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .price(Optional.ofNullable(product.getProductDetail())
                                .map(ProductDetail::getPrice)
                                .orElse(null))
                        .build()
                ).toList()
        );
    }

    @Override
    public Mono<PersistedProductResponse> updateSomeProductFields(Mono<UpdateProduct> updateProduct) {
        return productRepository.updateSomeProductFields(updateProduct.map(item -> {
            var product = new Product();
            product.setId(item.id());
            product.setName(item.name());
            if (item.price() != null || item.year() != null || item.color() != null) {
                var productDetail = new ProductDetail();
                productDetail.setPrice(item.price());
                productDetail.setYear(item.year());
                productDetail.setColor(item.color());
                productDetail.setCapacity(item.capacity());
                product.setProductDetail(productDetail);
            }
            return product;
        })).map(product -> PersistedProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .createdAt(product.getCreatedAt())
                .price(product.getProductDetail().getPrice())
                .build());
    }

    @Override
    public Mono<PersistedProductResponse> updateAllProductFields(Mono<UpdateProduct> updateProduct) {
        return productRepository.updateAllProductFields(updateProduct.map(item -> {
            var product = new Product();
            product.setId(item.id());
            product.setName(item.name());
            var productDetail = new ProductDetail();
            productDetail.setPrice(item.price());
            productDetail.setYear(item.year());
            productDetail.setColor(item.color());
            productDetail.setCapacity(item.capacity());
            product.setProductDetail(productDetail);
            return product;
        })).map(product -> PersistedProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .createdAt(product.getCreatedAt())
                .price(product.getProductDetail().getPrice())
                .build());
    }

    @Override
    public Mono<String> deleteProductById(String id) {
        return productRepository.deleteProductById(id);
    }

    @Override
    public Mono<PersistedProductDetailResponse> getProductById(String id) {
        return productRepository.findProductById(id).map(product -> PersistedProductDetailResponse.builder()
                .id(product.getId())
                .color(product.getProductDetail().getColor())
                .capacity(product.getProductDetail().getCapacity())
                .price(product.getProductDetail().getPrice())
                .createdAt(product.getCreatedAt())
                .build());
    }
}
