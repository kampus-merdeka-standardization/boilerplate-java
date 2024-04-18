package t.it.simplespringclient.applications.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;
import t.it.simplespringclient.applications.models.requests.AddProduct;
import t.it.simplespringclient.applications.models.responses.PersistedProductResponse;
import t.it.simplespringclient.applications.models.responses.WebResponse;
import t.it.simplespringclient.domains.services.ProductService;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@WebFluxTest
@ExtendWith(SpringExtension.class)
class ProductControllerTest {
    @MockBean
    private ProductService productService;

    @Autowired
    private WebTestClient webClient;

    @Test
    void testAddProduct_Success() {
        AddProduct addProduct = AddProduct.builder()
                .name("product-a")
                .color("red")
                .year(2020)
                .capacity("1 TB")
                .price(2000000.0).build();

        PersistedProductResponse persistedProductResponse = PersistedProductResponse.builder().price(addProduct.price()).name(addProduct.name()).id("1").build();

        when(productService.addProduct(any()).thenReturn(Mono.just(persistedProductResponse)));

        webClient.post().uri("/products").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromProducer(Mono.just(addProduct), AddProduct.class)).exchange().expectStatus().isCreated().expectBody(new ParameterizedTypeReference<WebResponse<PersistedProductResponse>>() {
        }).value(webResponse -> {
            PersistedProductResponse actualData = webResponse.data();
            assertEquals(persistedProductResponse.id(), actualData.id());
            assertEquals(persistedProductResponse.name(), actualData.name());
            assertEquals(String.valueOf(HttpStatus.CREATED.value()), webResponse.meta().code());
        });

        verify(productService, times(1)).addProduct(any());
    }
}