package vn.edu.iuh.fit.frontend.models;

import vn.edu.iuh.fit.backend.models.ProductPrice;
import vn.edu.iuh.fit.backend.services.ProductPriceServices;

import java.util.List;
import java.util.Optional;

public class ProductModel {
    private final ProductPriceServices productPriceServices;

    public ProductModel() {
        productPriceServices = new ProductPriceServices();
    }

    public List<ProductPrice> getProducts(int page) {
        return productPriceServices.getActiveProductsWithNewPrice(page);
    }

    public long getPages() {
        return productPriceServices.countPagesWithNewPrice();
    }

    public Optional<ProductPrice> getProduct(long productId) {
        return productPriceServices.getProductNewPrice(productId);
    }
}
