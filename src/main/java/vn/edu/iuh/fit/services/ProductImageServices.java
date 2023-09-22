package vn.edu.iuh.fit.services;

import jakarta.inject.Inject;
import vn.edu.iuh.fit.models.ProductImage;
import vn.edu.iuh.fit.repositories.ProductImageRepository;

import java.util.List;
import java.util.Optional;

public class ProductImageServices {
    @Inject
    private ProductImageRepository productImageRepository;

    public List<ProductImage> getAll(int page) {
        return productImageRepository.getAll(Math.max(1, page));
    }

    public Optional<ProductImage> findById(long id) {
        return productImageRepository.findById(id);
    }

    public boolean add(ProductImage productImage) {
        return productImageRepository.add(productImage);
    }

    public Optional<Boolean> update(ProductImage productImage) {
        Optional<ProductImage> optional = findById(productImage.getImage_id());

        if (optional.isEmpty())
            return Optional.empty();

        return Optional.of(productImageRepository.update(productImage));
    }

    public List<ProductImage> getByProductId(long productId) {
        return productImageRepository.getByProductId(productId);
    }
}
