package t.it.boilerplates.domains.repositories;

import t.it.boilerplates.domains.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    String addNewProduct(Product product);

    void updateProduct(Product product);

    List<Product> getProducts();

    Optional<Product> findById(String id);

    void deleteProduct(Product product);
}
