package t.it.simplespringclient.infrastructures.repositories.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import t.it.simplespringclient.domains.repositories.ProductRepository;
import t.it.simplespringclient.domains.repositories.entities.Product;
import t.it.simplespringclient.domains.repositories.entities.ProductDetail;
import t.it.simplespringclient.infrastructures.repositories.payloads.responses.MetaResponse;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(value = {MockitoExtension.class})
@SuppressWarnings("unchecked")
class ProductRepositoryImplTest {
    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriSpec;
    @Mock
    private WebClient.RequestBodySpec requestBodySpec;
    @Mock
    private WebClient.ResponseSpec responseSpec;
    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;
    @Mock
    private WebClient webClient;

    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepositoryImpl(webClient);
    }

    @Test
    void testAddProduct_Success() {
        var productDetail = new ProductDetail();
        productDetail.setYear(2020);
        productDetail.setPrice(2000.0);
        productDetail.setColor("Green");
        productDetail.setCapacity("1 TB");

        var product = new Product();
        product.setId("2");
        product.setName("a");
        product.setCreatedAt("2020-12-12");
        product.setProductDetail(productDetail);

        when(responseSpec.bodyToMono(Product.class)).thenReturn(Mono.just(product));
        when(requestHeadersUriSpec.retrieve()).thenReturn(responseSpec);
        when(requestBodySpec.body(any(), eq(Product.class))).thenReturn(requestHeadersUriSpec);
        when(requestBodySpec.contentType(any(MediaType.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.accept(any(MediaType.class))).thenReturn(requestBodySpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(webClient.post()).thenReturn(requestBodyUriSpec);

        Mono<Product> actualProduct = productRepository.addProduct(Mono.just(product));

        StepVerifier.create(actualProduct)
                .expectNext(product)
                .verifyComplete();

        verify(responseSpec, times(1)).bodyToMono(Product.class);
        verify(requestHeadersUriSpec, times(1)).retrieve();
        verify(requestBodySpec, times(1)).body(any(), eq(Product.class));
        verify(requestBodySpec, times(1)).contentType(any(MediaType.class));
        verify(requestBodySpec, times(1)).accept(any(MediaType.class));
        verify(requestBodyUriSpec, times(1)).uri(anyString());
        verify(webClient, times(1)).post();
    }

    @Test
    void testFindProducts_Success() {
        var productDetail = new ProductDetail();
        productDetail.setYear(2020);
        productDetail.setPrice(2000.0);
        productDetail.setColor("Green");
        productDetail.setCapacity("1 TB");

        var product = new Product();
        product.setId("2");
        product.setName("a");
        product.setCreatedAt("2020-12-12");
        product.setProductDetail(productDetail);

        when(responseSpec.bodyToMono(new ParameterizedTypeReference<List<Product>>() {
        })).thenReturn(Mono.just(Collections.singletonList(product)));
        when(requestHeadersUriSpec.retrieve()).thenReturn(responseSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.accept(any(MediaType.class))).thenReturn(requestHeadersUriSpec);
        when(webClient.get()).thenReturn(requestHeadersUriSpec);

        Mono<List<Product>> actualProducts = productRepository.findProducts();

        StepVerifier.create(actualProducts)
                .assertNext(products -> assertEquals(1, products.size()))
                .verifyComplete();

        verify(responseSpec, times(1)).bodyToMono(new ParameterizedTypeReference<List<Product>>() {
        });
        verify(requestHeadersUriSpec, times(1)).retrieve();
        verify(requestHeadersUriSpec, times(1)).uri(anyString());
        verify(requestHeadersUriSpec, times(1)).accept(any(MediaType.class));
        verify(webClient, times(1)).get();
    }

    @Test
    void testUpdateAllProductFields_Success() {
        var productDetail = new ProductDetail();
        productDetail.setYear(2020);
        productDetail.setPrice(2000.0);
        productDetail.setColor("Green");
        productDetail.setCapacity("1 TB");

        var product = new Product();
        product.setId("2");
        product.setName("a");
        product.setCreatedAt("2020-12-12");
        product.setProductDetail(productDetail);

        when(responseSpec.bodyToMono(Product.class)).thenReturn(Mono.just(product));
        when(requestHeadersUriSpec.retrieve()).thenReturn(responseSpec);
        when(requestBodySpec.body(any(), eq(Product.class))).thenReturn(requestHeadersUriSpec);
        when(requestBodySpec.contentType(any(MediaType.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.accept(any(MediaType.class))).thenReturn(requestBodySpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(webClient.put()).thenReturn(requestBodyUriSpec);

        Mono<Product> actualProduct = productRepository.updateAllProductFields(Mono.just(product));

        StepVerifier.create(actualProduct)
                .expectNext(product)
                .verifyComplete();

        verify(responseSpec, times(1)).bodyToMono(Product.class);
        verify(requestHeadersUriSpec, times(1)).retrieve();
        verify(requestBodySpec, times(1)).body(any(), eq(Product.class));
        verify(requestBodySpec, times(1)).contentType(any(MediaType.class));
        verify(requestBodySpec, times(1)).accept(any(MediaType.class));
        verify(requestBodyUriSpec, times(1)).uri(anyString());
        verify(webClient, times(1)).put();
    }

    @Test
    void testUpdateSomeProductFields_Success() {
        var productDetail = new ProductDetail();
        productDetail.setYear(2020);
        productDetail.setPrice(2000.0);
        productDetail.setColor("Green");
        productDetail.setCapacity("1 TB");

        var product = new Product();
        product.setId("2");
        product.setName("a");
        product.setCreatedAt("2020-12-12");
        product.setProductDetail(productDetail);

        when(responseSpec.bodyToMono(Product.class)).thenReturn(Mono.just(product));
        when(requestHeadersUriSpec.retrieve()).thenReturn(responseSpec);
        when(requestBodySpec.body(any(), eq(Product.class))).thenReturn(requestHeadersUriSpec);
        when(requestBodySpec.contentType(any(MediaType.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.accept(any(MediaType.class))).thenReturn(requestBodySpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(webClient.patch()).thenReturn(requestBodyUriSpec);

        Mono<Product> actualProduct = productRepository.updateSomeProductFields(Mono.just(product));

        StepVerifier.create(actualProduct)
                .expectNext(product)
                .verifyComplete();

        verify(responseSpec, times(1)).bodyToMono(Product.class);
        verify(requestHeadersUriSpec, times(1)).retrieve();
        verify(requestBodySpec, times(1)).body(any(), eq(Product.class));
        verify(requestBodySpec, times(1)).contentType(any(MediaType.class));
        verify(requestBodySpec, times(1)).accept(any(MediaType.class));
        verify(requestBodyUriSpec, times(1)).uri(anyString());
        verify(webClient, times(1)).patch();
    }

    @Test
    void testDeleteProductById_Success() {
        var dummyId = "1";

        MetaResponse metaResponse = MetaResponse.builder()
                .message("Successfully deleted")
                .build();

        when(responseSpec.bodyToMono(MetaResponse.class)).thenReturn(Mono.just(metaResponse));
        when(requestHeadersUriSpec.retrieve()).thenReturn(responseSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.accept(any(MediaType.class))).thenReturn(requestHeadersUriSpec);
        when(webClient.delete()).thenReturn(requestHeadersUriSpec);

        Mono<String> message = productRepository.deleteProductById(dummyId);

        StepVerifier.create(message)
                .expectNext(metaResponse.message())
                        .verifyComplete();

        verify(responseSpec, times(1)).bodyToMono(MetaResponse.class);
        verify(requestHeadersUriSpec, times(1)).retrieve();
        verify(requestHeadersUriSpec, times(1)).uri(anyString());
        verify(requestHeadersUriSpec, times(1)).accept(any(MediaType.class));
        verify(webClient, times(1)).delete();
    }

    @Test
    void testFindProductById_Success() {
        var dummyId = "1";

        var productDetail = new ProductDetail();
        productDetail.setYear(2020);
        productDetail.setPrice(2000.0);
        productDetail.setColor("Green");
        productDetail.setCapacity("1 TB");

        var product = new Product();
        product.setId(dummyId);
        product.setName("a");
        product.setCreatedAt("2020-12-12");
        product.setProductDetail(productDetail);

        when(responseSpec.bodyToMono(Product.class)).thenReturn(Mono.just(product));
        when(requestHeadersUriSpec.retrieve()).thenReturn(responseSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.accept(any(MediaType.class))).thenReturn(requestHeadersUriSpec);
        when(webClient.get()).thenReturn(requestHeadersUriSpec);

        Mono<Product> actualProduct = productRepository.findProductById(dummyId);

        StepVerifier.create(actualProduct)
                .expectNext(product)
                        .verifyComplete();

        verify(responseSpec, times(1)).bodyToMono(Product.class);
        verify(requestHeadersUriSpec, times(1)).retrieve();
        verify(requestHeadersUriSpec, times(1)).uri(anyString());
        verify(requestHeadersUriSpec, times(1)).accept(any(MediaType.class));
        verify(webClient, times(1)).get();
    }
}