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
import reactor.core.publisher.Mono;
import t.it.simplespringclient.domains.services.models.PersistedProductDetail;
import t.it.simplespringclient.domains.services.models.AddProduct;
import t.it.simplespringclient.domains.services.models.PersistedProduct;
import t.it.simplespringclient.applications.models.responses.WebResponse;
import t.it.simplespringclient.domains.services.ProductService;
import t.it.simplespringclient.domains.services.models.UpdateProduct;


import java.util.Collections;
import java.util.List;

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

        PersistedProduct persistedProduct = PersistedProduct.builder().price(addProduct.price()).name(addProduct.name()).id("1").build();

        when(productService.addProduct(any())).thenAnswer(invocation -> Mono.just(persistedProduct));

        webClient.post().uri("/products").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).body(Mono.just(addProduct), AddProduct.class).exchange().expectStatus().isCreated().expectBody(new ParameterizedTypeReference<WebResponse<PersistedProduct>>() {
        }).value(webResponse -> {
            PersistedProduct actualData = webResponse.data();
            assertEquals(persistedProduct.id(), actualData.id());
            assertEquals(persistedProduct.name(), actualData.name());
            assertEquals(String.valueOf(HttpStatus.CREATED.value()), webResponse.meta().code());
        });

        verify(productService, times(1)).addProduct(any());
    }

    @Test
    void testGetProducts_Success() {
        AddProduct addProduct = AddProduct.builder()
                .name("product-a")
                .color("red")
                .year(2020)
                .capacity("1 TB")
                .price(2000000.0).build();

        PersistedProduct persistedProduct = PersistedProduct.builder().price(addProduct.price()).name(addProduct.name()).id("1").build();

        when(productService.getProducts()).thenReturn(Mono.just(Collections.singletonList(persistedProduct)));

        webClient.get().uri("/products").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk().expectBody(new ParameterizedTypeReference<WebResponse<List<PersistedProduct>>>() {
        }).value(webResponse -> {
            assertEquals(1, webResponse.data().size());
            assertEquals(String.valueOf(HttpStatus.OK.value()), webResponse.meta().code());
        });

        verify(productService, times(1)).getProducts();
    }

    @Test
    void testGetProductById_Success() {
        var dummyId = "1";
        PersistedProductDetail persistedProductDetail = PersistedProductDetail.builder()
                .id(dummyId)
                .color("red")
                .price(2000000.0)
                .capacity("1 TB")
                .createdAt("2020-12-12").build();

        when(productService.getProductById(anyString())).thenReturn(Mono.just(persistedProductDetail));

        webClient.get().uri("/products/" + dummyId).accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk().expectBody(new ParameterizedTypeReference<WebResponse<PersistedProductDetail>>() {
        }).value(webResponse -> {
            assertEquals(persistedProductDetail, webResponse.data());
            assertEquals(String.valueOf(HttpStatus.OK.value()), webResponse.meta().code());
        });

        verify(productService, times(1)).getProductById(anyString());
    }

    @Test
    void testUpdateAllProductFieldsById_Success() {
        var dummyId = "1";
        UpdateProduct updateProduct = UpdateProduct.builder()
                .id(dummyId)
                .name("product-a")
                .color("red")
                .year(2020)
                .capacity("1 TB")
                .price(2000000.0).build();

        PersistedProduct persistedProduct = PersistedProduct.builder().price(updateProduct.price()).name(updateProduct.name()).id("1").build();

        when(productService.updateAllProductFields(any())).thenAnswer(invocation -> Mono.just(persistedProduct));

        webClient.put().uri("/products/" + dummyId).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).body(Mono.just(updateProduct), UpdateProduct.class).exchange().expectStatus().isOk().expectBody(new ParameterizedTypeReference<WebResponse<PersistedProduct>>() {
        }).value(webResponse -> {
            PersistedProduct actualData = webResponse.data();
            assertEquals(persistedProduct.id(), actualData.id());
            assertEquals(persistedProduct.name(), actualData.name());
            assertEquals(String.valueOf(HttpStatus.OK.value()), webResponse.meta().code());
        });

        verify(productService, times(1)).updateAllProductFields(any());
    }

    @Test
    void testUpdateSomeProductFieldsById_Success() {
        var dummyId = "1";
        UpdateProduct updateProduct = UpdateProduct.builder()
                .id(dummyId)
                .name("product-a")
                .year(2020)
                .capacity("1 TB")
                .price(2000000.0).build();

        PersistedProduct persistedProduct = PersistedProduct.builder().price(updateProduct.price()).name(updateProduct.name()).id("1").build();

        when(productService.updateSomeProductFields(any())).thenAnswer(invocation -> Mono.just(persistedProduct));

        webClient.patch().uri("/products/" + dummyId).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).body(Mono.just(updateProduct), UpdateProduct.class).exchange().expectStatus().isOk().expectBody(new ParameterizedTypeReference<WebResponse<PersistedProduct>>() {
        }).value(webResponse -> {
            PersistedProduct actualData = webResponse.data();
            assertEquals(persistedProduct.id(), actualData.id());
            assertEquals(persistedProduct.name(), actualData.name());
            assertEquals(String.valueOf(HttpStatus.OK.value()), webResponse.meta().code());
        });

        verify(productService, times(1)).updateSomeProductFields(any());
    }

    @Test
    void testDeleteProductById_Success() {
        var dummyId = "1";
        var dummyMessage = "Successfully deleted";

        when(productService.deleteProductById(anyString())).thenReturn(Mono.just(dummyMessage));

        webClient.delete().uri("/products/" + dummyId).accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk().expectBody(new ParameterizedTypeReference<WebResponse<Void>>() {
        }).value(webResponse -> {
            assertEquals(String.valueOf(HttpStatus.OK.value()), webResponse.meta().code());
            assertEquals(dummyMessage, webResponse.meta().message());
        });

        verify(productService, times(1)).deleteProductById(anyString());
    }
}