package t.it.boilerplates.infrastructures.repositories;

import org.springframework.stereotype.Repository;
import t.it.boilerplates.domains.entities.Product;
import t.it.boilerplates.domains.repositories.ProductRepository;

import java.util.*;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final Map<String, Product> products = new HashMap<>();

    @Override
    public String addNewProduct(Product product) {
        product.setId(UUID.randomUUID().toString());
        products.put(product.getId(), product);
        return product.getId();
    }

    @Override
    public void updateProduct(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public List<Product> getProducts() {
        return products.values().stream().toList();
    }

    @Override
    public Optional<Product> findById(String id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public void deleteProduct(Product product) {
        products.remove(product.getId());
    }
}
