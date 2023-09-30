package vn.edu.iuh.fit.frontend.models;

import vn.edu.iuh.fit.backend.services.CartDetailServices;

public class CartDetailModel {
    private final CartDetailServices cartDetailServices;

    public CartDetailModel() {
        cartDetailServices = new CartDetailServices();
    }

    public long countByCustomer(long customerId) {
        return cartDetailServices.countByCustomer(customerId);
    }
}
