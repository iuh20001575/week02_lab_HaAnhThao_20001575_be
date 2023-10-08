package vn.edu.iuh.fit.backend.services;

import jakarta.inject.Inject;
import vn.edu.iuh.fit.backend.enums.ProductStatus;
import vn.edu.iuh.fit.backend.models.Product;
import vn.edu.iuh.fit.backend.repositories.ProductRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProductServices {
    private final ProductRepository productRepository;

    public ProductServices() {
        this.productRepository = new ProductRepository();
    }

    public List<Product> getAll(int page) {
        return productRepository.getAll(Math.max(page, 1));
    }

    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }

    public boolean add(Product product) {
        return productRepository.add(product);
    }

    public Optional<Boolean> update(Product product) {
        Optional<Product> productFind = findById(product.getProduct_id());

        if (productFind.isEmpty())
            return Optional.empty();

        return Optional.of(productRepository.update(product));
    }

    public Optional<Boolean> delete(long id) {
        Optional<Product> product = findById(id);

        if (product.isEmpty())
            return Optional.empty();

        return Optional.of(productRepository.updateStatus(id, ProductStatus.DISCONTINUED));
    }

    public Map<Long, String> getProductIdAndNameInProductPrice() {
        return productRepository.getProductIdAndNameInProductPrice();
    }
}
