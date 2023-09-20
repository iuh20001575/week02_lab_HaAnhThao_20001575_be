package vn.edu.iuh.fit.services;

import jakarta.inject.Inject;
import vn.edu.iuh.fit.models.Product;
import vn.edu.iuh.fit.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

public class ProductServices {
    @Inject
    private ProductRepository productRepository;

    public List<Product> getAll(int page) {
        return productRepository.getAll(Math.max(page, 1));
    }

    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }
}
