package t.it.boilerplates.interfaces.grpc.controllers;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import t.it.boilerplates.applications.services.ProductService;
import t.it.boilerplates.controllers.ProductControllerGrpc;
import t.it.boilerplates.interfaces.models.requests.AddProductRequest;
import t.it.boilerplates.interfaces.models.requests.UpdateAllProductFieldRequest;
import t.it.boilerplates.interfaces.models.requests.UpdateSomeProductFieldsRequest;
import t.it.boilerplates.models.requests.AddProduct;
import t.it.boilerplates.models.requests.Empty;
import t.it.boilerplates.models.requests.ProductId;
import t.it.boilerplates.models.requests.UpdateProduct;
import t.it.boilerplates.models.responses.AddedProduct;
import t.it.boilerplates.models.responses.AddedProducts;
import t.it.boilerplates.models.responses.ProductDetail;
import t.it.boilerplates.models.responses.Web;

@RequiredArgsConstructor
@GrpcService
public class ProductGrpcController extends ProductControllerGrpc.ProductControllerImplBase {
    private final ProductService productService;

    @Override
    public void add(AddProduct request, StreamObserver<AddedProduct> responseObserver) {
        try {
            final var addProductRequest = AddProductRequest.builder().name(request.getName()).price(request.getPrice()).category(request.getCategory()).createdAt(request.getCreatedAt()).updatedAt(request.getUpdatedAt()).description(request.getDescription()).build();
            final var addedProductResponse = productService.addProduct(addProductRequest);
            responseObserver.onNext(AddedProduct.newBuilder().setId(addedProductResponse.id()).setName(addedProductResponse.name()).setPrice(addedProductResponse.price()).setCategory(addedProductResponse.category()).build());
            responseObserver.onCompleted();
        } catch (Exception exception) {
            responseObserver.onError(exception);
        }
    }

    @Override
    public void getById(ProductId request, StreamObserver<ProductDetail> responseObserver) {
        try {
            final var productDetailResponse = productService.getProductById(request.getId());
            responseObserver.onNext(ProductDetail.newBuilder().setId(productDetailResponse.id()).setName(productDetailResponse.name()).setPrice(productDetailResponse.price()).setCategory(productDetailResponse.category()).setDescription(productDetailResponse.description()).setCreatedAt(productDetailResponse.createdAt()).setUpdatedAt(productDetailResponse.updatedAt()).build());
            responseObserver.onCompleted();
        } catch (Exception exception) {
            responseObserver.onError(exception);
        }
    }

    @Override
    public void updateSomeFields(UpdateProduct request, StreamObserver<AddedProduct> responseObserver) {
        try {
            final var updateSomeProductFieldsRequest = UpdateSomeProductFieldsRequest.builder()
                    .id(request.getId())
                    .name(request.getName())
                    .updatedAt(request.getUpdatedAt())
                    .category(request.getCategory())
                    .price(request.getPrice())
                    .description(request.getDescription())
                    .build();
            final var addedProductResponse = productService.updateProduct(updateSomeProductFieldsRequest);
            responseObserver.onNext(AddedProduct.newBuilder().setId(addedProductResponse.id()).setName(addedProductResponse.name()).setPrice(addedProductResponse.price()).setCategory(addedProductResponse.category()).build());
            responseObserver.onCompleted();
        } catch (Exception exception) {
            responseObserver.onError(exception);
        }
    }

    @Override
    public void updateAllFields(UpdateProduct request, StreamObserver<AddedProduct> responseObserver) {
        try {
            final var updateAllProductFieldsRequest = UpdateAllProductFieldRequest.builder()
                    .id(request.getId())
                    .name(request.getName())
                    .updatedAt(request.getUpdatedAt())
                    .category(request.getCategory())
                    .price(request.getPrice())
                    .description(request.getDescription())
                    .build();
            final var addedProductResponse = productService.updateProduct(updateAllProductFieldsRequest);
            responseObserver.onNext(AddedProduct.newBuilder().setId(addedProductResponse.id()).setName(addedProductResponse.name()).setPrice(addedProductResponse.price()).setCategory(addedProductResponse.category()).build());
            responseObserver.onCompleted();
        } catch (Exception exception) {
            responseObserver.onError(exception);
        }
    }

    @Override
    public void remove(ProductId request, StreamObserver<Web> responseObserver) {
        try {
            productService.removeProductById(request.getId());
            responseObserver.onNext(Web.newBuilder().setMessage("OK").build());
            responseObserver.onCompleted();
        } catch (Exception exception) {
            responseObserver.onError(exception);
        }
    }

    @Override
    public void get(Empty request, StreamObserver<AddedProducts> responseObserver) {
        responseObserver.onNext(AddedProducts.newBuilder()
                .addAllProducts(productService.getProducts()
                        .stream()
                        .map(addedProductResponse ->
                                AddedProduct.newBuilder()
                                        .setId(addedProductResponse.id())
                                        .setName(addedProductResponse.name())
                                        .setPrice(addedProductResponse.price())
                                        .setCategory(addedProductResponse.category()
                                        ).build())
                        .toList())
                .build());
        responseObserver.onCompleted();
    }
}
