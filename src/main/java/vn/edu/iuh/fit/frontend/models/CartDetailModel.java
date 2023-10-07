package vn.edu.iuh.fit.frontend.models;

import vn.edu.iuh.fit.backend.models.CartDetail;
import vn.edu.iuh.fit.backend.services.CartDetailServices;

import java.util.List;
import java.util.Optional;

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

    public Optional<Boolean> delete(long productId, long cartId) {
        return cartDetailServices.delete(productId, cartId);
    }

    public boolean add(CartDetail cartDetail) {
        return cartDetailServices.add(cartDetail);
    }
}
