package vn.edu.iuh.fit.backend.services;

import vn.edu.iuh.fit.backend.models.ProductPrice;
import vn.edu.iuh.fit.backend.repositories.ProductPriceRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ProductPriceServices {
    private final ProductPriceRepository productPriceRepository;

    public ProductPriceServices() {
        productPriceRepository = new ProductPriceRepository();
    }

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

    public List<ProductPrice> getActiveProductsWithNewPrice(int page) {
        return productPriceRepository.getActiveProductsWithNewPrice(Math.max(page, 1));
    }

    public long countPagesWithNewPrice() {
        return (long) Math.ceil((double) productPriceRepository.countActiveProductsWithNewPrice() / 20);
    }

    public List<ProductPrice> getActiveProductsWithNewPriceByProductIds(List<Long> productIds) {
        return productPriceRepository.getActiveProductsWithNewPriceByProductIds(productIds);
    }
}
