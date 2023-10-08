package vn.edu.iuh.fit.frontend.models;

import vn.edu.iuh.fit.backend.models.ProductPrice;
import vn.edu.iuh.fit.backend.services.ProductPriceServices;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ProductPriceModel {
    private final ProductPriceServices productPriceServices;

    public ProductPriceModel() {
        productPriceServices = new ProductPriceServices();
    }

    public List<ProductPrice> getActiveProductsWithNewPriceByProductIds(List<Long> productIds) {
        return productPriceServices.getActiveProductsWithNewPriceByProductIds(productIds);
    }

    public Map<LocalDateTime, Double> getDateAndPriceByProductId(long productId) {
        return productPriceServices.getDateAndPriceByProductId(productId);
    }
}
