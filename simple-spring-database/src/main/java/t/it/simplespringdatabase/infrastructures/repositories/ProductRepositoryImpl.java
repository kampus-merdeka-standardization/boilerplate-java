package t.it.simplespringdatabase.infrastructures.repositories;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import t.it.simplespringdatabase.domains.repositories.ProductRepository;
import t.it.simplespringdatabase.domains.repositories.entities.Product;
import t.it.simplespringdatabase.infrastructures.repositories.mysql.MySqlProductDao;

@Repository
@Slf4j
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    private final MySqlProductDao mySqlProductDao;

    @Override
    public Mono<Product> addProduct(Product product) {
        return mySqlProductDao.save(product);
    }

    @Override
    public Flux<Product> findProducts() {
        return mySqlProductDao.findAll();
    }

    @Override
    public Mono<Product> updateProduct(Product product) {
        log.info("updating product {}", product);
        return mySqlProductDao.save(product);
    }

    @Override
    public Mono<Void> deleteProduct(Product product) {
        return mySqlProductDao
                .delete(product);
    }

    @Override
    public Mono<Product> findProductById(String id) {
        return mySqlProductDao.findById(id);
    }
}
