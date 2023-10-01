package vn.edu.iuh.fit.backend.services;

import vn.edu.iuh.fit.backend.models.CartDetail;
import vn.edu.iuh.fit.backend.repositories.CartDetailRepository;

import java.util.List;

public class CartDetailServices {
    private final CartDetailRepository cartDetailRepository;

    public CartDetailServices() {
        cartDetailRepository = new CartDetailRepository();
    }

    public long countByCustomer(long customerId) {
        return cartDetailRepository.countByCustomer(customerId);
    }

    public List<CartDetail> getCartDetailsByCustomerId(long customerId) {
        return cartDetailRepository.getCartDetailsByCustomerId(customerId);
    }
}
