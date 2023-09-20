package vn.edu.iuh.fit.services;

import jakarta.inject.Inject;
import vn.edu.iuh.fit.models.Product;
import vn.edu.iuh.fit.repositories.ProductRepository;

import java.util.List;

public class ProductServices {
    @Inject
    private ProductRepository productRepository;

    public List<Product> getAll(int page) {
        return productRepository.getAll(Math.max(page, 1));
    }
}
