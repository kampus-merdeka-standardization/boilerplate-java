package t.it.restclient.infrastructures.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import t.it.restclient.domains.entities.Product;
import t.it.restclient.domains.entities.ProductDetail;
import t.it.restclient.domains.repositories.ProductRepository;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ProductRepositoryImplTest {
    private static MockWebServer mockWebServer;

    private static ProductRepository productRepository;


    @BeforeAll
    static void beforeAll() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start(8080);
        WebClient mockedWebClient = WebClient.builder()
                .baseUrl(mockWebServer.url("/").toString())
                .build();
        productRepository = new ProductRepositoryImpl(mockedWebClient);
    }

    @AfterAll
    static void afterAll() throws IOException {
        mockWebServer.close();
    }

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void testInit() {
        assertNotNull(productRepository);
    }

    @DisplayName("when get products")
    @Test
    public void getProducts_Ok() throws JsonProcessingException {
        // arrange
        List<Product> dummyProducts = Stream.of(10).map(integer ->
                Product.builder()
                        .id(UUID.randomUUID().toString())
                        .name("my product - " + integer)
                        .productDetail(ProductDetail.builder()
                                .price(1999.99)
                                .year(2020)
                                .color("Midnight Blue")
                                .build())
                        .build()).toList();

        mockWebServer
                .enqueue(
                        new MockResponse()
                                .setHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                                .setResponseCode(HttpStatus.OK.value())
                                .setBody(objectMapper.writeValueAsString(dummyProducts))
                )
        ;


        // act
        Mono<List<Product>> responses = productRepository.products();

        // verify reactor
        StepVerifier.create(responses)
                .expectNextMatches(actualProducts -> actualProducts.equals(dummyProducts));
    }
}