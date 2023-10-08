package vn.edu.iuh.fit.frontend.models;

import vn.edu.iuh.fit.backend.models.ProductPrice;
import vn.edu.iuh.fit.backend.services.ProductPriceServices;
import vn.edu.iuh.fit.backend.services.ProductServices;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProductModel {
    private final ProductPriceServices productPriceServices;
    private final ProductServices productServices;

    public ProductModel() {
        productPriceServices = new ProductPriceServices();
        productServices = new ProductServices();
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

    public Map<Long, String> getProductIdAndNameInProductPrice() {
        return productServices.getProductIdAndNameInProductPrice();
    }
}
