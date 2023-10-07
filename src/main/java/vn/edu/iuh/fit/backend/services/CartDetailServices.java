package vn.edu.iuh.fit.backend.services;

import vn.edu.iuh.fit.backend.models.CartDetail;
import vn.edu.iuh.fit.backend.repositories.CartDetailRepository;

import java.util.List;
import java.util.Optional;

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

    public Optional<Boolean> updateQuantity(CartDetail cartDetail) {
        Optional<CartDetail> find = findById(cartDetail.getProduct().getProduct_id(), cartDetail.getCart().getCustomer().getId());

        if (find.isEmpty())
            return Optional.empty();

        return Optional.of(cartDetailRepository.update(cartDetail));
    }

    public Optional<CartDetail> findById(long productId, long cartId) {
        return cartDetailRepository.findById(productId, cartId);
    }

    public Optional<Boolean> delete(long productId, long cartId) {
        Optional<CartDetail> cartDetailOptional = findById(productId, cartId);

        if (cartDetailOptional.isEmpty())
            return Optional.empty();

        return Optional.of(cartDetailRepository.delete(productId, cartId));
    }

    public Optional<Boolean> addQty(CartDetail cartDetail) {
        Optional<CartDetail> cartDetailOptional = findById(cartDetail.getProduct().getProduct_id(), cartDetail.getCart().getCustomer().getId());

        if (cartDetailOptional.isEmpty())
            return Optional.empty();

        return Optional.of(cartDetailRepository.addQty(cartDetail));
    }

    public boolean add(CartDetail cartDetail) {
        Optional<CartDetail> cartDetailOptional = findById(cartDetail.getProduct().getProduct_id(), cartDetail.getCart().getCustomer().getId());

        if (cartDetailOptional.isEmpty())
            return cartDetailRepository.add(cartDetail);

        return cartDetailRepository.addQty(cartDetail);
    }
}
