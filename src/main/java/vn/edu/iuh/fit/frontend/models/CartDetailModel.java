package vn.edu.iuh.fit.frontend.models;

import vn.edu.iuh.fit.backend.models.CartDetail;
import vn.edu.iuh.fit.backend.services.CartDetailServices;

import java.util.List;

public class CartDetailModel {
    private final CartDetailServices cartDetailServices;

    public CartDetailModel() {
        cartDetailServices = new CartDetailServices();
    }

    public long countByCustomer(long customerId) {
        return cartDetailServices.countByCustomer(customerId);
    }

    public List<CartDetail> getCartDetailsByCustomerId(long customerId) {
        return cartDetailServices.getCartDetailsByCustomerId(customerId);
    }
}
