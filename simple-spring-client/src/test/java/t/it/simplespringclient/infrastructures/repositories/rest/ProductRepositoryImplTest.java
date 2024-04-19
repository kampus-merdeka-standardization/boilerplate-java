package t.it.simplespringclient.infrastructures.repositories.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import t.it.simplespringclient.domains.repositories.ProductRepository;
import t.it.simplespringclient.domains.repositories.entities.Product;
import t.it.simplespringclient.domains.repositories.entities.ProductDetail;

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
    void testFindFindProducts_Success() {

    }
}