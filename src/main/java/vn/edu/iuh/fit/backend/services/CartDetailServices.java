package vn.edu.iuh.fit.backend.services;

import vn.edu.iuh.fit.backend.repositories.CartDetailRepository;

public class CartDetailServices {
    private final CartDetailRepository cartDetailRepository;

    public CartDetailServices() {
        cartDetailRepository = new CartDetailRepository();
    }

    public long countByCustomer(long customerId) {
        return cartDetailRepository.countByCustomer(customerId);
    }
}
