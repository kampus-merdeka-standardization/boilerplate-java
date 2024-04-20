package t.it.simplespringclient.domains.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import t.it.simplespringclient.domains.repositories.ProductRepository;
import t.it.simplespringclient.domains.repositories.entities.Product;
import t.it.simplespringclient.domains.repositories.entities.ProductDetail;
import t.it.simplespringclient.domains.services.models.AddProduct;
import t.it.simplespringclient.domains.services.models.PersistedProduct;
import t.it.simplespringclient.domains.services.models.PersistedProductDetail;
import t.it.simplespringclient.domains.services.models.UpdateProduct;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    void testInit() {
        assertNotNull(productService);
    }

    @Test
    void testAddProduct() {
        ProductDetail productDetail = new ProductDetail();
        productDetail.setYear(2020);
        productDetail.setPrice(2000.0);
        productDetail.setCapacity("1 TB");
        productDetail.setColor("Blue");

        Product product = new Product();
        product.setId("1");
        product.setName("test");
        product.setCreatedAt("2020-12-12");
        product.setProductDetail(productDetail);

        AddProduct addProduct = AddProduct.builder()
                .name("test")
                .color("Blue")
                .price(2000.0)
                .year(2020)
                .build();

        when(productRepository.addProduct(any())).thenAnswer(invocation -> Mono.just(product));

        Mono<PersistedProduct> persistedProduct = productService.addProduct(Mono.just(addProduct));

        StepVerifier.create(persistedProduct)
                .assertNext(item -> assertEquals(product.getProductDetail().getPrice(), item.price()))
                .verifyComplete();

        verify(productRepository, times(1)).addProduct(any());
    }

    @Test
    void testGetProducts() {
        ProductDetail productDetail = new ProductDetail();
        productDetail.setYear(2020);
        productDetail.setPrice(2000.0);
        productDetail.setCapacity("1 TB");
        productDetail.setColor("Blue");

        Product product = new Product();
        product.setId("1");
        product.setName("test");
        product.setCreatedAt("2020-12-12");
        product.setProductDetail(productDetail);

        when(productRepository.findProducts()).thenReturn(Mono.just(Collections.singletonList(product)));

        Mono<List<PersistedProduct>> products = productService.getProducts();

        StepVerifier.create(products)
                .assertNext(persistedProducts -> assertEquals(1, persistedProducts.size()))
                .verifyComplete();

        verify(productRepository, times(1)).findProducts();
    }

    @Test
    void testUpdateAllProductFields() {
        ProductDetail productDetail = new ProductDetail();
        productDetail.setYear(2020);
        productDetail.setPrice(2000.0);
        productDetail.setCapacity("1 TB");
        productDetail.setColor("Blue");

        Product product = new Product();
        product.setId("1");
        product.setName("test");
        product.setCreatedAt("2020-12-12");
        product.setProductDetail(productDetail);

        UpdateProduct updateProduct = UpdateProduct.builder()
                .id("1")
                .name("test")
                .price(2000.0)
                .year(2020)
                .color("Blue")
                .build();

        when(productRepository.updateAllProductFields(any())).thenAnswer(invocation -> Mono.just(product));

        Mono<PersistedProduct> persistedProduct = productService.updateAllProductFields(Mono.just(updateProduct));

        StepVerifier.create(persistedProduct)
                .assertNext(item -> assertEquals(product.getProductDetail().getPrice(), item.price()))
                .verifyComplete();

        verify(productRepository, times(1)).updateAllProductFields(any());
    }

    @Test
    void testUpdateSomeProductFields() {
        ProductDetail productDetail = new ProductDetail();
        productDetail.setYear(2020);
        productDetail.setPrice(2000.0);
        productDetail.setCapacity("1 TB");
        productDetail.setColor("Blue");

        Product product = new Product();
        product.setId("1");
        product.setName("test");
        product.setCreatedAt("2020-12-12");
        product.setProductDetail(productDetail);

        UpdateProduct updateProduct = UpdateProduct.builder()
                .id("1")
                .name("test")
                .price(2000.0)
                .year(2020)
                .color("Blue")
                .build();

        when(productRepository.updateAllProductFields(any())).thenAnswer(invocation -> Mono.just(product));

        Mono<PersistedProduct> persistedProduct = productService.updateAllProductFields(Mono.just(updateProduct));

        StepVerifier.create(persistedProduct)
                .assertNext(item -> assertEquals(product.getProductDetail().getPrice(), item.price()))
                .verifyComplete();

        verify(productRepository, times(1)).updateAllProductFields(any());
    }

    @Test
    void testGetProductById() {
        var dummyId = "1";

        ProductDetail productDetail = new ProductDetail();
        productDetail.setYear(2020);
        productDetail.setPrice(2000.0);
        productDetail.setCapacity("1 TB");
        productDetail.setColor("Blue");

        Product product = new Product();
        product.setId("1");
        product.setName("test");
        product.setCreatedAt("2020-12-12");
        product.setProductDetail(productDetail);

        when(productRepository.findProductById(dummyId)).thenReturn(Mono.just(product));

        Mono<PersistedProductDetail> persistedProductDetail = productService.getProductById(dummyId);

        StepVerifier.create(persistedProductDetail)
                .assertNext(item -> assertEquals(product.getProductDetail().getPrice(), item.price()))
                .verifyComplete();

        verify(productRepository, times(1)).findProductById(dummyId);
    }
}