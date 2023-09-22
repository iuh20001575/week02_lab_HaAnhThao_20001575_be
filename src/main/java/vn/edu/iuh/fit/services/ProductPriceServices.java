package vn.edu.iuh.fit.services;

import jakarta.inject.Inject;
import vn.edu.iuh.fit.models.ProductPrice;
import vn.edu.iuh.fit.repositories.ProductPriceRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ProductPriceServices {
    @Inject
    private ProductPriceRepository productPriceRepository;

    public List<ProductPrice> getAll(int page) {
        return productPriceRepository.getAll(Math.max(page, 1));
    }

    public Optional<ProductPrice> findById(LocalDateTime id, long productId) {
        return productPriceRepository.findById(id, productId);
    }

    public boolean add(ProductPrice productPrice) {
        return productPriceRepository.add(productPrice);
    }

    public Optional<Boolean> update(ProductPrice productPrice) {
        Optional<ProductPrice> productPriceFind = findById(productPrice.getPrice_date_time(), productPrice.getProduct().getProduct_id());

        if (productPriceFind.isEmpty())
            return Optional.empty();

        return Optional.of(productPriceRepository.update(productPrice));
    }

    public Optional<ProductPrice> getProductNewPrice(long productId) {
        return productPriceRepository.getProductNewPrice(productId);
    }
}
