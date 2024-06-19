package t.it.simplespringdatabase.infrastructures.repositories.mysql;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import t.it.simplespringdatabase.domains.repositories.entities.Product;

// it becomes DAO
@Component
public interface MySqlProductDao extends R2dbcRepository<Product, String> {
    Flux<Product> findAllByOrderByCreatedAtDesc();
}