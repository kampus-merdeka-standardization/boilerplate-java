package t.it.simplespringdatabase.domains.services;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import t.it.simplespringdatabase.commons.exceptions.ConflictResourceException;
import t.it.simplespringdatabase.commons.exceptions.NotFoundResourceException;
import t.it.simplespringdatabase.domains.repositories.ProductRepository;
import t.it.simplespringdatabase.domains.repositories.entities.Product;
import t.it.simplespringdatabase.domains.services.models.*;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ValidationService validationService;

    @Override
    public Mono<PersistedProduct> addProduct(Mono<AddProduct> addProduct) {
        return addProduct
                .flatMap(
                        productItem ->
                                validationService.validateObject(productItem)
                                        .map(Optional::of)
                                        .defaultIfEmpty(Optional.empty())
                                        .flatMap(validationException -> validationException.<Mono<? extends Product>>map(Mono::error).orElseGet(() -> Mono.just(
                                                Product.builder()
                                                        .id(UUID.randomUUID().toString())
                                                        .name(productItem.name())
                                                        .description(productItem.description())
                                                        .price(productItem.price())
                                                        .quantity(productItem.quantity())
                                                        .createdAt(productItem.createdAt())
                                                        .updatedAt(productItem.createdAt())
                                                        .build()
                                        )))
                )
                .flatMap(
                        productRepository::addProduct
                ).map(product -> PersistedProduct.builder()
                        .id(product.id())
                        .name(product.name())
                        .price(product.price())
                        .createdAt(product.createdAt())
                        .updatedAt(product.updatedAt())
                        .build())
                .subscribeOn(Schedulers.parallel());
    }

    @Override
    public Flux<PersistedProduct> getProducts() {
        return productRepository.findProducts()
                .map(
                        product -> PersistedProduct.builder()
                                .id(product.id())
                                .name(product.name())
                                .price(product.price())
                                .createdAt(product.createdAt())
                                .updatedAt(product.updatedAt())
                                .build()
                )
                .subscribeOn(Schedulers.parallel());
    }

    @Override
    @Transactional
    public Mono<PersistedProduct> updateSomeProductFields(Mono<UpdateSomeProduct> updateProduct) {
        return updateProduct
                .flatMap(updateSomeProduct ->
                        validationService.validateObject(updateSomeProduct)
                                .map(Optional::of)
                                .defaultIfEmpty(Optional.empty())
                                .flatMap(validationException ->
                                        validationException.<Mono<? extends Product>>map(Mono::error).orElseGet(() ->
                                                        productRepository
                                                                .findProductById(updateSomeProduct.id())
                                                                .switchIfEmpty(Mono.error(new NotFoundResourceException("the product is not found")))
                                                                .onErrorResume(throwable -> throwable instanceof OptimisticLockingFailureException, throwable -> Mono.error(new ConflictResourceException("the product is already changed by others")))
                                                )
                                                .flatMap(productEntity -> {
                                                    Product.ProductBuilder productBuilder = productEntity.toBuilder();
                                                    Optional.ofNullable(updateSomeProduct.name()).ifPresent(productBuilder::name);
                                                    Optional.ofNullable(updateSomeProduct.description()).ifPresent(productBuilder::description);
                                                    Optional.ofNullable(updateSomeProduct.quantity()).ifPresent(productBuilder::quantity);
                                                    Optional.ofNullable(updateSomeProduct.price()).ifPresent(productBuilder::price);

                                                    return productRepository.updateProduct(
                                                            productBuilder
                                                                    .updatedAt(updateSomeProduct.updatedAt())
                                                                    .build()
                                                    );
                                                })
                                                .map(productEntity -> PersistedProduct.builder()
                                                        .id(productEntity.id())
                                                        .name(productEntity.name())
                                                        .price(productEntity.price())
                                                        .createdAt(productEntity.createdAt())
                                                        .updatedAt(productEntity.updatedAt())
                                                        .build()
                                                )
                                                .subscribeOn(Schedulers.parallel())
                                )
                )
                .subscribeOn(Schedulers.parallel());
    }

    @Override
    @Transactional
    public Mono<PersistedProduct> updateAllProductFields(Mono<UpdateAllProduct> updateProduct) {
        return
                updateProduct
                        .flatMap(updateAllProduct ->
                                validationService.validateObject(updateAllProduct)
                                        .map(Optional::of)
                                        .defaultIfEmpty(Optional.empty())
                                        .flatMap(validationException -> validationException.<Mono<? extends Product>>map(Mono::error).orElseGet(() ->
                                                productRepository
                                                        .findProductById(updateAllProduct.id())
                                                        .switchIfEmpty(Mono.error(new NotFoundResourceException("the product is not found")))
                                                        .onErrorResume(throwable -> throwable instanceof OptimisticLockingFailureException, throwable -> Mono.error(new ConflictResourceException("the product is already changed by others")))
                                        ))
                                        .flatMap(product -> productRepository.updateProduct(
                                                product
                                                        .withCreatedAt(product.createdAt())
                                                        .withName(updateAllProduct.name())
                                                        .withDescription(updateAllProduct.description())
                                                        .withPrice(updateAllProduct.price())
                                                        .withUpdatedAt(updateAllProduct.updatedAt())
                                                        .withQuantity(updateAllProduct.quantity())
                                        ))
                                        .map(product -> PersistedProduct.builder()
                                                .id(product.id())
                                                .name(product.name())
                                                .price(product.price())
                                                .createdAt(product.createdAt())
                                                .updatedAt(product.updatedAt())
                                                .build())
                                        .subscribeOn(Schedulers.parallel())
                        )
                        .subscribeOn(Schedulers.parallel());
    }

    @Transactional
    @Override
    public Mono<String> deleteProductById(String id) {
        return productRepository.findProductById(id)
                .switchIfEmpty(Mono.error(new NotFoundResourceException("the product is not found")))
                .onErrorResume(throwable -> throwable instanceof OptimisticLockingFailureException, throwable -> Mono.error(new ConflictResourceException("the product is already changed by others")))
                .flatMap(product ->
                        productRepository.deleteProduct(product)
                                .subscribeOn(Schedulers.parallel()).then(Mono.fromCallable(product::id)))
                .subscribeOn(Schedulers.parallel());
    }

    @Override
    public Mono<PersistedProductDetail> getProductById(String id) {
        return productRepository.findProductById(id)
                .switchIfEmpty(Mono.error(new NotFoundResourceException("the product is not found")))
                .map(product -> PersistedProductDetail.builder()
                        .id(product.id())
                        .name(product.name())
                        .price(product.price())
                        .quantity(product.quantity())
                        .description(product.description())
                        .createdAt(product.createdAt())
                        .updatedAt(product.updatedAt())
                        .build())
                .subscribeOn(Schedulers.parallel());
    }
}
