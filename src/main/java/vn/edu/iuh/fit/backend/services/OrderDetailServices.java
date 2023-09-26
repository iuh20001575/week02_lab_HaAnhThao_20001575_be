package vn.edu.iuh.fit.backend.services;

import jakarta.inject.Inject;
import vn.edu.iuh.fit.backend.models.OrderDetail;
import vn.edu.iuh.fit.backend.repositories.OrderDetailRepository;

import java.util.List;
import java.util.Optional;

public class OrderDetailServices {
    @Inject
    private OrderDetailRepository orderDetailRepository;

    public List<OrderDetail> getAll(int page) {
        return orderDetailRepository.getAll(Math.max(page, 1));
    }

    public Optional<OrderDetail> findById(long orderId, long productId) {
        return orderDetailRepository.findById(orderId, productId);
    }

    public boolean add(OrderDetail orderDetail) {
        return orderDetailRepository.add(orderDetail);
    }

    public Optional<Boolean> update(OrderDetail orderDetail) {
        Optional<OrderDetail> orderDetailFind = findById(orderDetail.getOrder().getOrder_id(), orderDetail.getProduct().getProduct_id());

        if (orderDetailFind.isEmpty())
            return Optional.empty();

        return Optional.of(orderDetailRepository.update(orderDetail));
    }
}
